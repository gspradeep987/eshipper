import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IJobType, JobType } from '../job-type.model';
import { JobTypeService } from '../service/job-type.service';
import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';
import { SisyphusJobService } from 'app/entities/sisyphus-job/service/sisyphus-job.service';

@Component({
  selector: 'jhi-job-type-update',
  templateUrl: './job-type-update.component.html',
})
export class JobTypeUpdateComponent implements OnInit {
  isSaving = false;

  sisyphusJobsSharedCollection: ISisyphusJob[] = [];

  editForm = this.fb.group({
    id: [],
    iD: [],
    nAME: [],
    dESCRIPTION: [],
    jobType: [],
  });

  constructor(
    protected jobTypeService: JobTypeService,
    protected sisyphusJobService: SisyphusJobService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ jobType }) => {
      this.updateForm(jobType);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const jobType = this.createFromForm();
    if (jobType.id !== undefined) {
      this.subscribeToSaveResponse(this.jobTypeService.update(jobType));
    } else {
      this.subscribeToSaveResponse(this.jobTypeService.create(jobType));
    }
  }

  trackSisyphusJobById(index: number, item: ISisyphusJob): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IJobType>>): void {
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

  protected updateForm(jobType: IJobType): void {
    this.editForm.patchValue({
      id: jobType.id,
      iD: jobType.iD,
      nAME: jobType.nAME,
      dESCRIPTION: jobType.dESCRIPTION,
      jobType: jobType.jobType,
    });

    this.sisyphusJobsSharedCollection = this.sisyphusJobService.addSisyphusJobToCollectionIfMissing(
      this.sisyphusJobsSharedCollection,
      jobType.jobType
    );
  }

  protected loadRelationshipsOptions(): void {
    this.sisyphusJobService
      .query()
      .pipe(map((res: HttpResponse<ISisyphusJob[]>) => res.body ?? []))
      .pipe(
        map((sisyphusJobs: ISisyphusJob[]) =>
          this.sisyphusJobService.addSisyphusJobToCollectionIfMissing(sisyphusJobs, this.editForm.get('jobType')!.value)
        )
      )
      .subscribe((sisyphusJobs: ISisyphusJob[]) => (this.sisyphusJobsSharedCollection = sisyphusJobs));
  }

  protected createFromForm(): IJobType {
    return {
      ...new JobType(),
      id: this.editForm.get(['id'])!.value,
      iD: this.editForm.get(['iD'])!.value,
      nAME: this.editForm.get(['nAME'])!.value,
      dESCRIPTION: this.editForm.get(['dESCRIPTION'])!.value,
      jobType: this.editForm.get(['jobType'])!.value,
    };
  }
}
