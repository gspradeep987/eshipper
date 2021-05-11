import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ISisyphusSubJob, SisyphusSubJob } from '../sisyphus-sub-job.model';
import { SisyphusSubJobService } from '../service/sisyphus-sub-job.service';
import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';
import { SisyphusJobService } from 'app/entities/sisyphus-job/service/sisyphus-job.service';

@Component({
  selector: 'jhi-sisyphus-sub-job-update',
  templateUrl: './sisyphus-sub-job-update.component.html',
})
export class SisyphusSubJobUpdateComponent implements OnInit {
  isSaving = false;

  sisyphusJobsSharedCollection: ISisyphusJob[] = [];

  editForm = this.fb.group({
    id: [],
    runOrder: [],
    sisyphusJob: [],
  });

  constructor(
    protected sisyphusSubJobService: SisyphusSubJobService,
    protected sisyphusJobService: SisyphusJobService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sisyphusSubJob }) => {
      this.updateForm(sisyphusSubJob);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sisyphusSubJob = this.createFromForm();
    if (sisyphusSubJob.id !== undefined) {
      this.subscribeToSaveResponse(this.sisyphusSubJobService.update(sisyphusSubJob));
    } else {
      this.subscribeToSaveResponse(this.sisyphusSubJobService.create(sisyphusSubJob));
    }
  }

  trackSisyphusJobById(index: number, item: ISisyphusJob): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISisyphusSubJob>>): void {
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

  protected updateForm(sisyphusSubJob: ISisyphusSubJob): void {
    this.editForm.patchValue({
      id: sisyphusSubJob.id,
      runOrder: sisyphusSubJob.runOrder,
      sisyphusJob: sisyphusSubJob.sisyphusJob,
    });

    this.sisyphusJobsSharedCollection = this.sisyphusJobService.addSisyphusJobToCollectionIfMissing(
      this.sisyphusJobsSharedCollection,
      sisyphusSubJob.sisyphusJob
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

  protected createFromForm(): ISisyphusSubJob {
    return {
      ...new SisyphusSubJob(),
      id: this.editForm.get(['id'])!.value,
      runOrder: this.editForm.get(['runOrder'])!.value,
      sisyphusJob: this.editForm.get(['sisyphusJob'])!.value,
    };
  }
}
