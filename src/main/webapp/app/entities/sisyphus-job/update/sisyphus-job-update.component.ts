import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ISisyphusJob, SisyphusJob } from '../sisyphus-job.model';
import { SisyphusJobService } from '../service/sisyphus-job.service';

@Component({
  selector: 'jhi-sisyphus-job-update',
  templateUrl: './sisyphus-job-update.component.html',
})
export class SisyphusJobUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    scheduleMinute: [],
    scheduleHour: [],
    scheduleDay: [],
    scheduleMonth: [],
    shouldrunYN: [],
    retries: [],
    moniterScheduleYN: [],
  });

  constructor(protected sisyphusJobService: SisyphusJobService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sisyphusJob }) => {
      this.updateForm(sisyphusJob);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sisyphusJob = this.createFromForm();
    if (sisyphusJob.id !== undefined) {
      this.subscribeToSaveResponse(this.sisyphusJobService.update(sisyphusJob));
    } else {
      this.subscribeToSaveResponse(this.sisyphusJobService.create(sisyphusJob));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISisyphusJob>>): void {
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

  protected updateForm(sisyphusJob: ISisyphusJob): void {
    this.editForm.patchValue({
      id: sisyphusJob.id,
      name: sisyphusJob.name,
      scheduleMinute: sisyphusJob.scheduleMinute,
      scheduleHour: sisyphusJob.scheduleHour,
      scheduleDay: sisyphusJob.scheduleDay,
      scheduleMonth: sisyphusJob.scheduleMonth,
      shouldrunYN: sisyphusJob.shouldrunYN,
      retries: sisyphusJob.retries,
      moniterScheduleYN: sisyphusJob.moniterScheduleYN,
    });
  }

  protected createFromForm(): ISisyphusJob {
    return {
      ...new SisyphusJob(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      scheduleMinute: this.editForm.get(['scheduleMinute'])!.value,
      scheduleHour: this.editForm.get(['scheduleHour'])!.value,
      scheduleDay: this.editForm.get(['scheduleDay'])!.value,
      scheduleMonth: this.editForm.get(['scheduleMonth'])!.value,
      shouldrunYN: this.editForm.get(['shouldrunYN'])!.value,
      retries: this.editForm.get(['retries'])!.value,
      moniterScheduleYN: this.editForm.get(['moniterScheduleYN'])!.value,
    };
  }
}
