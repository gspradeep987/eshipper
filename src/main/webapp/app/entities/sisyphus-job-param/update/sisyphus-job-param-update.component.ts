import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ISisyphusJobParam, SisyphusJobParam } from '../sisyphus-job-param.model';
import { SisyphusJobParamService } from '../service/sisyphus-job-param.service';
import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';
import { SisyphusJobService } from 'app/entities/sisyphus-job/service/sisyphus-job.service';

@Component({
  selector: 'jhi-sisyphus-job-param-update',
  templateUrl: './sisyphus-job-param-update.component.html',
})
export class SisyphusJobParamUpdateComponent implements OnInit {
  isSaving = false;

  sisyphusJobsSharedCollection: ISisyphusJob[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    value: [],
    sisyphusJob: [],
  });

  constructor(
    protected sisyphusJobParamService: SisyphusJobParamService,
    protected sisyphusJobService: SisyphusJobService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sisyphusJobParam }) => {
      this.updateForm(sisyphusJobParam);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sisyphusJobParam = this.createFromForm();
    if (sisyphusJobParam.id !== undefined) {
      this.subscribeToSaveResponse(this.sisyphusJobParamService.update(sisyphusJobParam));
    } else {
      this.subscribeToSaveResponse(this.sisyphusJobParamService.create(sisyphusJobParam));
    }
  }

  trackSisyphusJobById(index: number, item: ISisyphusJob): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISisyphusJobParam>>): void {
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

  protected updateForm(sisyphusJobParam: ISisyphusJobParam): void {
    this.editForm.patchValue({
      id: sisyphusJobParam.id,
      name: sisyphusJobParam.name,
      value: sisyphusJobParam.value,
      sisyphusJob: sisyphusJobParam.sisyphusJob,
    });

    this.sisyphusJobsSharedCollection = this.sisyphusJobService.addSisyphusJobToCollectionIfMissing(
      this.sisyphusJobsSharedCollection,
      sisyphusJobParam.sisyphusJob
    );
  }

  protected loadRelationshipsOptions(): void {
    this.sisyphusJobService
      .query()
      .pipe(map((res: HttpResponse<ISisyphusJob[]>) => res.body ?? []))
      .pipe(
        map((sisyphusJobs: ISisyphusJob[]) =>
          this.sisyphusJobService.addSisyphusJobToCollectionIfMissing(sisyphusJobs, this.editForm.get('sisyphusJob')!.value)
        )
      )
      .subscribe((sisyphusJobs: ISisyphusJob[]) => (this.sisyphusJobsSharedCollection = sisyphusJobs));
  }

  protected createFromForm(): ISisyphusJobParam {
    return {
      ...new SisyphusJobParam(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      value: this.editForm.get(['value'])!.value,
      sisyphusJob: this.editForm.get(['sisyphusJob'])!.value,
    };
  }
}
