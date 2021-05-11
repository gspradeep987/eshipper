import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ISisyphusClasses, SisyphusClasses } from '../sisyphus-classes.model';
import { SisyphusClassesService } from '../service/sisyphus-classes.service';
import { ISisyphusSubJob } from 'app/entities/sisyphus-sub-job/sisyphus-sub-job.model';
import { SisyphusSubJobService } from 'app/entities/sisyphus-sub-job/service/sisyphus-sub-job.service';

@Component({
  selector: 'jhi-sisyphus-classes-update',
  templateUrl: './sisyphus-classes-update.component.html',
})
export class SisyphusClassesUpdateComponent implements OnInit {
  isSaving = false;

  sisyphusSubJobsSharedCollection: ISisyphusSubJob[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    description: [],
    sisyphusClasses: [],
  });

  constructor(
    protected sisyphusClassesService: SisyphusClassesService,
    protected sisyphusSubJobService: SisyphusSubJobService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sisyphusClasses }) => {
      this.updateForm(sisyphusClasses);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sisyphusClasses = this.createFromForm();
    if (sisyphusClasses.id !== undefined) {
      this.subscribeToSaveResponse(this.sisyphusClassesService.update(sisyphusClasses));
    } else {
      this.subscribeToSaveResponse(this.sisyphusClassesService.create(sisyphusClasses));
    }
  }

  trackSisyphusSubJobById(index: number, item: ISisyphusSubJob): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISisyphusClasses>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(sisyphusClasses: ISisyphusClasses): void {
    this.editForm.patchValue({
      id: sisyphusClasses.id,
      name: sisyphusClasses.name,
      description: sisyphusClasses.description,
      sisyphusClasses: sisyphusClasses.sisyphusClasses,
    });

    this.sisyphusSubJobsSharedCollection = this.sisyphusSubJobService.addSisyphusSubJobToCollectionIfMissing(
      this.sisyphusSubJobsSharedCollection,
      sisyphusClasses.sisyphusClasses
    );
  }

  protected loadRelationshipsOptions(): void {
    this.sisyphusSubJobService
      .query()
      .pipe(map((res: HttpResponse<ISisyphusSubJob[]>) => res.body ?? []))
      .pipe(
        map((sisyphusSubJobs: ISisyphusSubJob[]) =>
          this.sisyphusSubJobService.addSisyphusSubJobToCollectionIfMissing(sisyphusSubJobs, this.editForm.get('sisyphusClasses')!.value)
        )
      )
      .subscribe((sisyphusSubJobs: ISisyphusSubJob[]) => (this.sisyphusSubJobsSharedCollection = sisyphusSubJobs));
  }

  protected createFromForm(): ISisyphusClasses {
    return {
      ...new SisyphusClasses(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      sisyphusClasses: this.editForm.get(['sisyphusClasses'])!.value,
    };
  }
}
