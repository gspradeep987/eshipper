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
    iD: [],
    nAME: [],
    sCHEDULEMINUTE: [],
    sCHEDULEHOUR: [],
    sCHEDULEDAY: [],
    sCHEDULEMONTH: [],
    sHOULDRUNYN: [],
    rETRIES: [],
    mONITORSCHEDULEYN: [],
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
      iD: sisyphusJob.iD,
      nAME: sisyphusJob.nAME,
      sCHEDULEMINUTE: sisyphusJob.sCHEDULEMINUTE,
      sCHEDULEHOUR: sisyphusJob.sCHEDULEHOUR,
      sCHEDULEDAY: sisyphusJob.sCHEDULEDAY,
      sCHEDULEMONTH: sisyphusJob.sCHEDULEMONTH,
      sHOULDRUNYN: sisyphusJob.sHOULDRUNYN,
      rETRIES: sisyphusJob.rETRIES,
      mONITORSCHEDULEYN: sisyphusJob.mONITORSCHEDULEYN,
    });
  }

  protected createFromForm(): ISisyphusJob {
    return {
      ...new SisyphusJob(),
      id: this.editForm.get(['id'])!.value,
      iD: this.editForm.get(['iD'])!.value,
      nAME: this.editForm.get(['nAME'])!.value,
      sCHEDULEMINUTE: this.editForm.get(['sCHEDULEMINUTE'])!.value,
      sCHEDULEHOUR: this.editForm.get(['sCHEDULEHOUR'])!.value,
      sCHEDULEDAY: this.editForm.get(['sCHEDULEDAY'])!.value,
      sCHEDULEMONTH: this.editForm.get(['sCHEDULEMONTH'])!.value,
      sHOULDRUNYN: this.editForm.get(['sHOULDRUNYN'])!.value,
      rETRIES: this.editForm.get(['rETRIES'])!.value,
      mONITORSCHEDULEYN: this.editForm.get(['mONITORSCHEDULEYN'])!.value,
    };
  }
}
