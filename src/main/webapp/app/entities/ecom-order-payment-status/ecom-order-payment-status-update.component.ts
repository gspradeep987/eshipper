import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomOrderPaymentStatus, EcomOrderPaymentStatus } from 'app/shared/model/ecom-order-payment-status.model';
import { EcomOrderPaymentStatusService } from './ecom-order-payment-status.service';

@Component({
  selector: 'jhi-ecom-order-payment-status-update',
  templateUrl: './ecom-order-payment-status-update.component.html',
})
export class EcomOrderPaymentStatusUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(50)]],
    value: [null, [Validators.maxLength(50)]],
  });

  constructor(
    protected ecomOrderPaymentStatusService: EcomOrderPaymentStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomOrderPaymentStatus }) => {
      this.updateForm(ecomOrderPaymentStatus);
    });
  }

  updateForm(ecomOrderPaymentStatus: IEcomOrderPaymentStatus): void {
    this.editForm.patchValue({
      id: ecomOrderPaymentStatus.id,
      name: ecomOrderPaymentStatus.name,
      value: ecomOrderPaymentStatus.value,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomOrderPaymentStatus = this.createFromForm();
    if (ecomOrderPaymentStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomOrderPaymentStatusService.update(ecomOrderPaymentStatus));
    } else {
      this.subscribeToSaveResponse(this.ecomOrderPaymentStatusService.create(ecomOrderPaymentStatus));
    }
  }

  private createFromForm(): IEcomOrderPaymentStatus {
    return {
      ...new EcomOrderPaymentStatus(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      value: this.editForm.get(['value'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomOrderPaymentStatus>>): void {
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
