import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomOrder, EcomOrder } from 'app/shared/model/ecom-order.model';
import { EcomOrderService } from './ecom-order.service';

@Component({
  selector: 'jhi-ecom-order-update',
  templateUrl: './ecom-order-update.component.html'
})
export class EcomOrderUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: []
  });

  constructor(protected ecomOrderService: EcomOrderService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomOrder }) => {
      this.updateForm(ecomOrder);
    });
  }

  updateForm(ecomOrder: IEcomOrder): void {
    this.editForm.patchValue({
      id: ecomOrder.id
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomOrder = this.createFromForm();
    if (ecomOrder.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomOrderService.update(ecomOrder));
    } else {
      this.subscribeToSaveResponse(this.ecomOrderService.create(ecomOrder));
    }
  }

  private createFromForm(): IEcomOrder {
    return {
      ...new EcomOrder(),
      id: this.editForm.get(['id'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomOrder>>): void {
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
