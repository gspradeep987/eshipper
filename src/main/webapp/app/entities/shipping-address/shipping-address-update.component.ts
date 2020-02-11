import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IShippingAddress, ShippingAddress } from 'app/shared/model/shipping-address.model';
import { ShippingAddressService } from './shipping-address.service';

@Component({
  selector: 'jhi-shipping-address-update',
  templateUrl: './shipping-address-update.component.html'
})
export class ShippingAddressUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: []
  });

  constructor(
    protected shippingAddressService: ShippingAddressService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ shippingAddress }) => {
      this.updateForm(shippingAddress);
    });
  }

  updateForm(shippingAddress: IShippingAddress): void {
    this.editForm.patchValue({
      id: shippingAddress.id
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

  private createFromForm(): IShippingAddress {
    return {
      ...new ShippingAddress(),
      id: this.editForm.get(['id'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IShippingAddress>>): void {
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
