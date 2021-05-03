import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IEcomWarehouse, EcomWarehouse } from '../ecom-warehouse.model';
import { EcomWarehouseService } from '../service/ecom-warehouse.service';

@Component({
  selector: 'jhi-ecom-warehouse-update',
  templateUrl: './ecom-warehouse-update.component.html',
})
export class EcomWarehouseUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(50)]],
    address: [null, [Validators.maxLength(50)]],
  });

  constructor(protected ecomWarehouseService: EcomWarehouseService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomWarehouse }) => {
      this.updateForm(ecomWarehouse);
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

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomWarehouse>>): void {
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

  protected updateForm(ecomWarehouse: IEcomWarehouse): void {
    this.editForm.patchValue({
      id: ecomWarehouse.id,
      name: ecomWarehouse.name,
      address: ecomWarehouse.address,
    });
  }

  protected createFromForm(): IEcomWarehouse {
    return {
      ...new EcomWarehouse(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      address: this.editForm.get(['address'])!.value,
    };
  }
}
