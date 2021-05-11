import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ISisyphusJobType, SisyphusJobType } from '../sisyphus-job-type.model';
import { SisyphusJobTypeService } from '../service/sisyphus-job-type.service';
import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';
import { SisyphusJobService } from 'app/entities/sisyphus-job/service/sisyphus-job.service';

@Component({
  selector: 'jhi-sisyphus-job-type-update',
  templateUrl: './sisyphus-job-type-update.component.html',
})
export class SisyphusJobTypeUpdateComponent implements OnInit {
  isSaving = false;

  sisyphusJobsSharedCollection: ISisyphusJob[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    description: [],
    sisyphusJobType: [],
  });

  constructor(
    protected sisyphusJobTypeService: SisyphusJobTypeService,
    protected sisyphusJobService: SisyphusJobService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sisyphusJobType }) => {
      this.updateForm(sisyphusJobType);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sisyphusJobType = this.createFromForm();
    if (sisyphusJobType.id !== undefined) {
      this.subscribeToSaveResponse(this.sisyphusJobTypeService.update(sisyphusJobType));
    } else {
      this.subscribeToSaveResponse(this.sisyphusJobTypeService.create(sisyphusJobType));
    }
  }

  trackSisyphusJobById(index: number, item: ISisyphusJob): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISisyphusJobType>>): void {
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

  protected updateForm(sisyphusJobType: ISisyphusJobType): void {
    this.editForm.patchValue({
      id: sisyphusJobType.id,
      name: sisyphusJobType.name,
      description: sisyphusJobType.description,
      sisyphusJobType: sisyphusJobType.sisyphusJobType,
    });

    this.sisyphusJobsSharedCollection = this.sisyphusJobService.addSisyphusJobToCollectionIfMissing(
      this.sisyphusJobsSharedCollection,
      sisyphusJobType.sisyphusJobType
    );
  }

  protected loadRelationshipsOptions(): void {
    this.sisyphusJobService
      .query()
      .pipe(map((res: HttpResponse<ISisyphusJob[]>) => res.body ?? []))
      .pipe(
        map((sisyphusJobs: ISisyphusJob[]) =>
          this.sisyphusJobService.addSisyphusJobToCollectionIfMissing(sisyphusJobs, this.editForm.get('sisyphusJobType')!.value)
        )
      )
      .subscribe((sisyphusJobs: ISisyphusJob[]) => (this.sisyphusJobsSharedCollection = sisyphusJobs));
  }

  protected createFromForm(): ISisyphusJobType {
    return {
      ...new SisyphusJobType(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      sisyphusJobType: this.editForm.get(['sisyphusJobType'])!.value,
    };
  }
}
