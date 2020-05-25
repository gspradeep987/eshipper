import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomOrderFullfillmentStatus, EcomOrderFullfillmentStatus } from 'app/shared/model/ecom-order-fullfillment-status.model';
import { EcomOrderFullfillmentStatusService } from './ecom-order-fullfillment-status.service';

@Component({
  selector: 'jhi-ecom-order-fullfillment-status-update',
  templateUrl: './ecom-order-fullfillment-status-update.component.html',
})
export class EcomOrderFullfillmentStatusUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(50)]],
    value: [null, [Validators.maxLength(50)]],
  });

  constructor(
    protected ecomOrderFullfillmentStatusService: EcomOrderFullfillmentStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomOrderFullfillmentStatus }) => {
      this.updateForm(ecomOrderFullfillmentStatus);
    });
  }

  updateForm(ecomOrderFullfillmentStatus: IEcomOrderFullfillmentStatus): void {
    this.editForm.patchValue({
      id: ecomOrderFullfillmentStatus.id,
      name: ecomOrderFullfillmentStatus.name,
      value: ecomOrderFullfillmentStatus.value,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomOrderFullfillmentStatus = this.createFromForm();
    if (ecomOrderFullfillmentStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomOrderFullfillmentStatusService.update(ecomOrderFullfillmentStatus));
    } else {
      this.subscribeToSaveResponse(this.ecomOrderFullfillmentStatusService.create(ecomOrderFullfillmentStatus));
    }
  }

  private createFromForm(): IEcomOrderFullfillmentStatus {
    return {
      ...new EcomOrderFullfillmentStatus(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      value: this.editForm.get(['value'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomOrderFullfillmentStatus>>): void {
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
