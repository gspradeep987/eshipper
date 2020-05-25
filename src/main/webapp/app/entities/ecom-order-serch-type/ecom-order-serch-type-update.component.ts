import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEcomOrderSerchType, EcomOrderSerchType } from 'app/shared/model/ecom-order-serch-type.model';
import { EcomOrderSerchTypeService } from './ecom-order-serch-type.service';

@Component({
  selector: 'jhi-ecom-order-serch-type-update',
  templateUrl: './ecom-order-serch-type-update.component.html',
})
export class EcomOrderSerchTypeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(50)]],
    value: [null, [Validators.maxLength(50)]],
  });

  constructor(
    protected ecomOrderSerchTypeService: EcomOrderSerchTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomOrderSerchType }) => {
      this.updateForm(ecomOrderSerchType);
    });
  }

  updateForm(ecomOrderSerchType: IEcomOrderSerchType): void {
    this.editForm.patchValue({
      id: ecomOrderSerchType.id,
      name: ecomOrderSerchType.name,
      value: ecomOrderSerchType.value,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomOrderSerchType = this.createFromForm();
    if (ecomOrderSerchType.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomOrderSerchTypeService.update(ecomOrderSerchType));
    } else {
      this.subscribeToSaveResponse(this.ecomOrderSerchTypeService.create(ecomOrderSerchType));
    }
  }

  private createFromForm(): IEcomOrderSerchType {
    return {
      ...new EcomOrderSerchType(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      value: this.editForm.get(['value'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomOrderSerchType>>): void {
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
