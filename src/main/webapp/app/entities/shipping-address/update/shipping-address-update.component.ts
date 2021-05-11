import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IShippingAddress, ShippingAddress } from '../shipping-address.model';
import { ShippingAddressService } from '../service/shipping-address.service';

@Component({
  selector: 'jhi-shipping-address-update',
  templateUrl: './shipping-address-update.component.html',
})
export class ShippingAddressUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
  });

  constructor(
    protected shippingAddressService: ShippingAddressService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shippingAddress }) => {
      this.updateForm(shippingAddress);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const shippingAddress = this.createFromForm();
    if (shippingAddress.id !== undefined) {
      this.subscribeToSaveResponse(this.shippingAddressService.update(shippingAddress));
    } else {
      this.subscribeToSaveResponse(this.shippingAddressService.create(shippingAddress));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShippingAddress>>): void {
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

  protected updateForm(shippingAddress: IShippingAddress): void {
    this.editForm.patchValue({
      id: shippingAddress.id,
    });
  }

  protected createFromForm(): IShippingAddress {
    return {
      ...new ShippingAddress(),
      id: this.editForm.get(['id'])!.value,
    };
  }
}
