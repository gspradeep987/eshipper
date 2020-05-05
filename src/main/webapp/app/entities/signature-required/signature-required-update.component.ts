import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISignatureRequired, SignatureRequired } from 'app/shared/model/signature-required.model';
import { SignatureRequiredService } from './signature-required.service';

@Component({
  selector: 'jhi-signature-required-update',
  templateUrl: './signature-required-update.component.html'
})
export class SignatureRequiredUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: []
  });

  constructor(
    protected signatureRequiredService: SignatureRequiredService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ signatureRequired }) => {
      this.updateForm(signatureRequired);
    });
  }

  updateForm(signatureRequired: ISignatureRequired): void {
    this.editForm.patchValue({
      id: signatureRequired.id
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const signatureRequired = this.createFromForm();
    if (signatureRequired.id !== undefined) {
      this.subscribeToSaveResponse(this.signatureRequiredService.update(signatureRequired));
    } else {
      this.subscribeToSaveResponse(this.signatureRequiredService.create(signatureRequired));
    }
  }

  private createFromForm(): ISignatureRequired {
    return {
      ...new SignatureRequired(),
      id: this.editForm.get(['id'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISignatureRequired>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
