import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICompanyEcomSettings, CompanyEcomSettings } from 'app/shared/model/company-ecom-settings.model';
import { CompanyEcomSettingsService } from './company-ecom-settings.service';
import { ISignatureRequired } from 'app/shared/model/signature-required.model';
import { SignatureRequiredService } from 'app/entities/signature-required/signature-required.service';
import { ICarrierSettings } from 'app/shared/model/carrier-settings.model';
import { CarrierSettingsService } from 'app/entities/carrier-settings/carrier-settings.service';

type SelectableEntity = ISignatureRequired | ICarrierSettings;

@Component({
  selector: 'jhi-company-ecom-settings-update',
  templateUrl: './company-ecom-settings-update.component.html'
})
export class CompanyEcomSettingsUpdateComponent implements OnInit {
  isSaving = false;
  signaturerequireds: ISignatureRequired[] = [];
  carriersettings: ICarrierSettings[] = [];

  editForm = this.fb.group({
    id: [],
    notifyRecipient: [],
    createPackingList: [],
    createPackingSlip: [],
    ecomModule: [],
    includeTaxesInRvenues: [],
    showAvgCustomerValue: [],
    showAvgOrderValue: [],
    showAvgShipmentValue: [],
    removeSerialReturners: [],
    showPackageStatistics: [],
    includeAllItemsRetCustomers: [],
    signatureRequiredId: [],
    carrierSettings: []
  });

  constructor(
    protected companyEcomSettingsService: CompanyEcomSettingsService,
    protected signatureRequiredService: SignatureRequiredService,
    protected carrierSettingsService: CarrierSettingsService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ companyEcomSettings }) => {
      this.updateForm(companyEcomSettings);

      this.signatureRequiredService
        .query()
        .subscribe((res: HttpResponse<ISignatureRequired[]>) => (this.signaturerequireds = res.body || []));

      this.carrierSettingsService.query().subscribe((res: HttpResponse<ICarrierSettings[]>) => (this.carriersettings = res.body || []));
    });
  }

  updateForm(companyEcomSettings: ICompanyEcomSettings): void {
    this.editForm.patchValue({
      id: companyEcomSettings.id,
      notifyRecipient: companyEcomSettings.notifyRecipient,
      createPackingList: companyEcomSettings.createPackingList,
      createPackingSlip: companyEcomSettings.createPackingSlip,
      ecomModule: companyEcomSettings.ecomModule,
      includeTaxesInRvenues: companyEcomSettings.includeTaxesInRvenues,
      showAvgCustomerValue: companyEcomSettings.showAvgCustomerValue,
      showAvgOrderValue: companyEcomSettings.showAvgOrderValue,
      showAvgShipmentValue: companyEcomSettings.showAvgShipmentValue,
      removeSerialReturners: companyEcomSettings.removeSerialReturners,
      showPackageStatistics: companyEcomSettings.showPackageStatistics,
      includeAllItemsRetCustomers: companyEcomSettings.includeAllItemsRetCustomers,
      signatureRequiredId: companyEcomSettings.signatureRequiredId,
      carrierSettings: companyEcomSettings.carrierSettings
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const companyEcomSettings = this.createFromForm();
    if (companyEcomSettings.id !== undefined) {
      this.subscribeToSaveResponse(this.companyEcomSettingsService.update(companyEcomSettings));
    } else {
      this.subscribeToSaveResponse(this.companyEcomSettingsService.create(companyEcomSettings));
    }
  }

  private createFromForm(): ICompanyEcomSettings {
    return {
      ...new CompanyEcomSettings(),
      id: this.editForm.get(['id'])!.value,
      notifyRecipient: this.editForm.get(['notifyRecipient'])!.value,
      createPackingList: this.editForm.get(['createPackingList'])!.value,
      createPackingSlip: this.editForm.get(['createPackingSlip'])!.value,
      ecomModule: this.editForm.get(['ecomModule'])!.value,
      includeTaxesInRvenues: this.editForm.get(['includeTaxesInRvenues'])!.value,
      showAvgCustomerValue: this.editForm.get(['showAvgCustomerValue'])!.value,
      showAvgOrderValue: this.editForm.get(['showAvgOrderValue'])!.value,
      showAvgShipmentValue: this.editForm.get(['showAvgShipmentValue'])!.value,
      removeSerialReturners: this.editForm.get(['removeSerialReturners'])!.value,
      showPackageStatistics: this.editForm.get(['showPackageStatistics'])!.value,
      includeAllItemsRetCustomers: this.editForm.get(['includeAllItemsRetCustomers'])!.value,
      signatureRequiredId: this.editForm.get(['signatureRequiredId'])!.value,
      carrierSettings: this.editForm.get(['carrierSettings'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICompanyEcomSettings>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }

  getSelected(selectedVals: ICarrierSettings[], option: ICarrierSettings): ICarrierSettings {
    if (selectedVals) {
      for (let i = 0; i < selectedVals.length; i++) {
        if (option.id === selectedVals[i].id) {
          return selectedVals[i];
        }
      }
    }
    return option;
  }
}
