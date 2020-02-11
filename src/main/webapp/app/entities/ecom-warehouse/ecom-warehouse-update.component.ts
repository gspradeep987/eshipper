import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomWarehouse, EcomWarehouse } from 'app/shared/model/ecom-warehouse.model';
import { EcomWarehouseService } from './ecom-warehouse.service';

@Component({
  selector: 'jhi-ecom-warehouse-update',
  templateUrl: './ecom-warehouse-update.component.html'
})
export class EcomWarehouseUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(50)]],
    address: [null, [Validators.maxLength(50)]]
  });

  constructor(protected ecomWarehouseService: EcomWarehouseService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomWarehouse }) => {
      this.updateForm(ecomWarehouse);
    });
  }

  updateForm(ecomWarehouse: IEcomWarehouse): void {
    this.editForm.patchValue({
      id: ecomWarehouse.id,
      name: ecomWarehouse.name,
      address: ecomWarehouse.address
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomWarehouse = this.createFromForm();
    if (ecomWarehouse.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomWarehouseService.update(ecomWarehouse));
    } else {
      this.subscribeToSaveResponse(this.ecomWarehouseService.create(ecomWarehouse));
    }
  }

  private createFromForm(): IEcomWarehouse {
    return {
      ...new EcomWarehouse(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      address: this.editForm.get(['address'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomWarehouse>>): void {
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
