import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IEcomStoreShipmentSettings, EcomStoreShipmentSettings } from '../ecom-store-shipment-settings.model';
import { EcomStoreShipmentSettingsService } from '../service/ecom-store-shipment-settings.service';

@Component({
  selector: 'jhi-ecom-store-shipment-settings-update',
  templateUrl: './ecom-store-shipment-settings-update.component.html',
})
export class EcomStoreShipmentSettingsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    shippingMode: [null, [Validators.maxLength(20)]],
    signatureReqd: [null, [Validators.maxLength(255)]],
    schedulePickup: [],
    liveRates: [],
    addResidential: [],
    tailgateAtDestination: [],
    tailgateAtSource: [],
  });

  constructor(
    protected ecomStoreShipmentSettingsService: EcomStoreShipmentSettingsService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStoreShipmentSettings }) => {
      this.updateForm(ecomStoreShipmentSettings);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomStoreShipmentSettings = this.createFromForm();
    if (ecomStoreShipmentSettings.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomStoreShipmentSettingsService.update(ecomStoreShipmentSettings));
    } else {
      this.subscribeToSaveResponse(this.ecomStoreShipmentSettingsService.create(ecomStoreShipmentSettings));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomStoreShipmentSettings>>): void {
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

  protected updateForm(ecomStoreShipmentSettings: IEcomStoreShipmentSettings): void {
    this.editForm.patchValue({
      id: ecomStoreShipmentSettings.id,
      shippingMode: ecomStoreShipmentSettings.shippingMode,
      signatureReqd: ecomStoreShipmentSettings.signatureReqd,
      schedulePickup: ecomStoreShipmentSettings.schedulePickup,
      liveRates: ecomStoreShipmentSettings.liveRates,
      addResidential: ecomStoreShipmentSettings.addResidential,
      tailgateAtDestination: ecomStoreShipmentSettings.tailgateAtDestination,
      tailgateAtSource: ecomStoreShipmentSettings.tailgateAtSource,
    });
  }

  protected createFromForm(): IEcomStoreShipmentSettings {
    return {
      ...new EcomStoreShipmentSettings(),
      id: this.editForm.get(['id'])!.value,
      shippingMode: this.editForm.get(['shippingMode'])!.value,
      signatureReqd: this.editForm.get(['signatureReqd'])!.value,
      schedulePickup: this.editForm.get(['schedulePickup'])!.value,
      liveRates: this.editForm.get(['liveRates'])!.value,
      addResidential: this.editForm.get(['addResidential'])!.value,
      tailgateAtDestination: this.editForm.get(['tailgateAtDestination'])!.value,
      tailgateAtSource: this.editForm.get(['tailgateAtSource'])!.value,
    };
  }
}
