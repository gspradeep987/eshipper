import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IEcomStore, EcomStore } from '../ecom-store.model';
import { EcomStoreService } from '../service/ecom-store.service';
import { IEcomStoreAddress } from 'app/entities/ecom-store-address/ecom-store-address.model';
import { EcomStoreAddressService } from 'app/entities/ecom-store-address/service/ecom-store-address.service';
import { IEcomStoreColorTheme } from 'app/entities/ecom-store-color-theme/ecom-store-color-theme.model';
import { EcomStoreColorThemeService } from 'app/entities/ecom-store-color-theme/service/ecom-store-color-theme.service';
import { IEcomStoreShipmentSettings } from 'app/entities/ecom-store-shipment-settings/ecom-store-shipment-settings.model';
import { EcomStoreShipmentSettingsService } from 'app/entities/ecom-store-shipment-settings/service/ecom-store-shipment-settings.service';
import { IEcomStorePackageSettings } from 'app/entities/ecom-store-package-settings/ecom-store-package-settings.model';
import { EcomStorePackageSettingsService } from 'app/entities/ecom-store-package-settings/service/ecom-store-package-settings.service';
import { IEcomStoreMarkup } from 'app/entities/ecom-store-markup/ecom-store-markup.model';
import { EcomStoreMarkupService } from 'app/entities/ecom-store-markup/service/ecom-store-markup.service';
import { ICompany } from 'app/entities/company/company.model';
import { CompanyService } from 'app/entities/company/service/company.service';
import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';
import { IEcomProduct } from 'app/entities/ecom-product/ecom-product.model';
import { EcomProductService } from 'app/entities/ecom-product/service/ecom-product.service';
import { IShipmentService } from 'app/entities/shipment-service/shipment-service.model';
import { ShipmentServiceService } from 'app/entities/shipment-service/service/shipment-service.service';

@Component({
  selector: 'jhi-ecom-store-update',
  templateUrl: './ecom-store-update.component.html',
})
export class EcomStoreUpdateComponent implements OnInit {
  isSaving = false;

  ecomStoreAddressesCollection: IEcomStoreAddress[] = [];
  ecomStoreColorThemesCollection: IEcomStoreColorTheme[] = [];
  ecomStoreShipmentSettingsCollection: IEcomStoreShipmentSettings[] = [];
  ecomStorePackageSettingsCollection: IEcomStorePackageSettings[] = [];
  ecomStoreMarkupsCollection: IEcomStoreMarkup[] = [];
  companiesSharedCollection: ICompany[] = [];
  usersSharedCollection: IUser[] = [];
  ecomProductsSharedCollection: IEcomProduct[] = [];
  shipmentServicesSharedCollection: IShipmentService[] = [];

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.maxLength(255)]],
    nickName: [null, [Validators.maxLength(255)]],
    apiPassword: [null, [Validators.maxLength(255)]],
    domain: [null, [Validators.maxLength(255)]],
    syncFrequency: [],
    syncInterval: [null, [Validators.maxLength(25)]],
    active: [],
    isManualSync: [],
    createdDate: [],
    updatedDate: [],
    ecomStoreAddress: [],
    ecomStoreColorTheme: [],
    ecomStoreShipmentSettings: [],
    ecomStorePackageSettings: [],
    ecomStoreMarkup: [],
    company: [],
    user: [],
    ecomProduct: [],
    shipmentServices: [],
  });

  constructor(
    protected ecomStoreService: EcomStoreService,
    protected ecomStoreAddressService: EcomStoreAddressService,
    protected ecomStoreColorThemeService: EcomStoreColorThemeService,
    protected ecomStoreShipmentSettingsService: EcomStoreShipmentSettingsService,
    protected ecomStorePackageSettingsService: EcomStorePackageSettingsService,
    protected ecomStoreMarkupService: EcomStoreMarkupService,
    protected companyService: CompanyService,
    protected userService: UserService,
    protected ecomProductService: EcomProductService,
    protected shipmentServiceService: ShipmentServiceService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStore }) => {
      this.updateForm(ecomStore);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ecomStore = this.createFromForm();
    if (ecomStore.id !== undefined) {
      this.subscribeToSaveResponse(this.ecomStoreService.update(ecomStore));
    } else {
      this.subscribeToSaveResponse(this.ecomStoreService.create(ecomStore));
    }
  }

  trackEcomStoreAddressById(index: number, item: IEcomStoreAddress): number {
    return item.id!;
  }

  trackEcomStoreColorThemeById(index: number, item: IEcomStoreColorTheme): number {
    return item.id!;
  }

  trackEcomStoreShipmentSettingsById(index: number, item: IEcomStoreShipmentSettings): number {
    return item.id!;
  }

  trackEcomStorePackageSettingsById(index: number, item: IEcomStorePackageSettings): number {
    return item.id!;
  }

  trackEcomStoreMarkupById(index: number, item: IEcomStoreMarkup): number {
    return item.id!;
  }

  trackCompanyById(index: number, item: ICompany): number {
    return item.id!;
  }

  trackUserById(index: number, item: IUser): number {
    return item.id!;
  }

  trackEcomProductById(index: number, item: IEcomProduct): number {
    return item.id!;
  }

  trackShipmentServiceById(index: number, item: IShipmentService): number {
    return item.id!;
  }

  getSelectedShipmentService(option: IShipmentService, selectedVals?: IShipmentService[]): IShipmentService {
    if (selectedVals) {
      for (const selectedVal of selectedVals) {
        if (option.id === selectedVal.id) {
          return selectedVal;
        }
      }
    }
    return option;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomStore>>): void {
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

  protected updateForm(ecomStore: IEcomStore): void {
    this.editForm.patchValue({
      id: ecomStore.id,
      name: ecomStore.name,
      nickName: ecomStore.nickName,
      apiPassword: ecomStore.apiPassword,
      domain: ecomStore.domain,
      syncFrequency: ecomStore.syncFrequency,
      syncInterval: ecomStore.syncInterval,
      active: ecomStore.active,
      isManualSync: ecomStore.isManualSync,
      createdDate: ecomStore.createdDate,
      updatedDate: ecomStore.updatedDate,
      ecomStoreAddress: ecomStore.ecomStoreAddress,
      ecomStoreColorTheme: ecomStore.ecomStoreColorTheme,
      ecomStoreShipmentSettings: ecomStore.ecomStoreShipmentSettings,
      ecomStorePackageSettings: ecomStore.ecomStorePackageSettings,
      ecomStoreMarkup: ecomStore.ecomStoreMarkup,
      company: ecomStore.company,
      user: ecomStore.user,
      ecomProduct: ecomStore.ecomProduct,
      shipmentServices: ecomStore.shipmentServices,
    });

    this.ecomStoreAddressesCollection = this.ecomStoreAddressService.addEcomStoreAddressToCollectionIfMissing(
      this.ecomStoreAddressesCollection,
      ecomStore.ecomStoreAddress
    );
    this.ecomStoreColorThemesCollection = this.ecomStoreColorThemeService.addEcomStoreColorThemeToCollectionIfMissing(
      this.ecomStoreColorThemesCollection,
      ecomStore.ecomStoreColorTheme
    );
    this.ecomStoreShipmentSettingsCollection = this.ecomStoreShipmentSettingsService.addEcomStoreShipmentSettingsToCollectionIfMissing(
      this.ecomStoreShipmentSettingsCollection,
      ecomStore.ecomStoreShipmentSettings
    );
    this.ecomStorePackageSettingsCollection = this.ecomStorePackageSettingsService.addEcomStorePackageSettingsToCollectionIfMissing(
      this.ecomStorePackageSettingsCollection,
      ecomStore.ecomStorePackageSettings
    );
    this.ecomStoreMarkupsCollection = this.ecomStoreMarkupService.addEcomStoreMarkupToCollectionIfMissing(
      this.ecomStoreMarkupsCollection,
      ecomStore.ecomStoreMarkup
    );
    this.companiesSharedCollection = this.companyService.addCompanyToCollectionIfMissing(this.companiesSharedCollection, ecomStore.company);
    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing(this.usersSharedCollection, ecomStore.user);
    this.ecomProductsSharedCollection = this.ecomProductService.addEcomProductToCollectionIfMissing(
      this.ecomProductsSharedCollection,
      ecomStore.ecomProduct
    );
    this.shipmentServicesSharedCollection = this.shipmentServiceService.addShipmentServiceToCollectionIfMissing(
      this.shipmentServicesSharedCollection,
      ...(ecomStore.shipmentServices ?? [])
    );
  }

  protected loadRelationshipsOptions(): void {
    this.ecomStoreAddressService
      .query({ filter: 'ecomstore-is-null' })
      .pipe(map((res: HttpResponse<IEcomStoreAddress[]>) => res.body ?? []))
      .pipe(
        map((ecomStoreAddresses: IEcomStoreAddress[]) =>
          this.ecomStoreAddressService.addEcomStoreAddressToCollectionIfMissing(
            ecomStoreAddresses,
            this.editForm.get('ecomStoreAddress')!.value
          )
        )
      )
      .subscribe((ecomStoreAddresses: IEcomStoreAddress[]) => (this.ecomStoreAddressesCollection = ecomStoreAddresses));

    this.ecomStoreColorThemeService
      .query({ filter: 'ecomstore-is-null' })
      .pipe(map((res: HttpResponse<IEcomStoreColorTheme[]>) => res.body ?? []))
      .pipe(
        map((ecomStoreColorThemes: IEcomStoreColorTheme[]) =>
          this.ecomStoreColorThemeService.addEcomStoreColorThemeToCollectionIfMissing(
            ecomStoreColorThemes,
            this.editForm.get('ecomStoreColorTheme')!.value
          )
        )
      )
      .subscribe((ecomStoreColorThemes: IEcomStoreColorTheme[]) => (this.ecomStoreColorThemesCollection = ecomStoreColorThemes));

    this.ecomStoreShipmentSettingsService
      .query({ filter: 'ecomstore-is-null' })
      .pipe(map((res: HttpResponse<IEcomStoreShipmentSettings[]>) => res.body ?? []))
      .pipe(
        map((ecomStoreShipmentSettings: IEcomStoreShipmentSettings[]) =>
          this.ecomStoreShipmentSettingsService.addEcomStoreShipmentSettingsToCollectionIfMissing(
            ecomStoreShipmentSettings,
            this.editForm.get('ecomStoreShipmentSettings')!.value
          )
        )
      )
      .subscribe(
        (ecomStoreShipmentSettings: IEcomStoreShipmentSettings[]) => (this.ecomStoreShipmentSettingsCollection = ecomStoreShipmentSettings)
      );

    this.ecomStorePackageSettingsService
      .query({ filter: 'ecomstore-is-null' })
      .pipe(map((res: HttpResponse<IEcomStorePackageSettings[]>) => res.body ?? []))
      .pipe(
        map((ecomStorePackageSettings: IEcomStorePackageSettings[]) =>
          this.ecomStorePackageSettingsService.addEcomStorePackageSettingsToCollectionIfMissing(
            ecomStorePackageSettings,
            this.editForm.get('ecomStorePackageSettings')!.value
          )
        )
      )
      .subscribe(
        (ecomStorePackageSettings: IEcomStorePackageSettings[]) => (this.ecomStorePackageSettingsCollection = ecomStorePackageSettings)
      );

    this.ecomStoreMarkupService
      .query({ filter: 'ecomstore-is-null' })
      .pipe(map((res: HttpResponse<IEcomStoreMarkup[]>) => res.body ?? []))
      .pipe(
        map((ecomStoreMarkups: IEcomStoreMarkup[]) =>
          this.ecomStoreMarkupService.addEcomStoreMarkupToCollectionIfMissing(ecomStoreMarkups, this.editForm.get('ecomStoreMarkup')!.value)
        )
      )
      .subscribe((ecomStoreMarkups: IEcomStoreMarkup[]) => (this.ecomStoreMarkupsCollection = ecomStoreMarkups));

    this.companyService
      .query()
      .pipe(map((res: HttpResponse<ICompany[]>) => res.body ?? []))
      .pipe(
        map((companies: ICompany[]) => this.companyService.addCompanyToCollectionIfMissing(companies, this.editForm.get('company')!.value))
      )
      .subscribe((companies: ICompany[]) => (this.companiesSharedCollection = companies));

    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(map((users: IUser[]) => this.userService.addUserToCollectionIfMissing(users, this.editForm.get('user')!.value)))
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));

    this.ecomProductService
      .query()
      .pipe(map((res: HttpResponse<IEcomProduct[]>) => res.body ?? []))
      .pipe(
        map((ecomProducts: IEcomProduct[]) =>
          this.ecomProductService.addEcomProductToCollectionIfMissing(ecomProducts, this.editForm.get('ecomProduct')!.value)
        )
      )
      .subscribe((ecomProducts: IEcomProduct[]) => (this.ecomProductsSharedCollection = ecomProducts));

    this.shipmentServiceService
      .query()
      .pipe(map((res: HttpResponse<IShipmentService[]>) => res.body ?? []))
      .pipe(
        map((shipmentServices: IShipmentService[]) =>
          this.shipmentServiceService.addShipmentServiceToCollectionIfMissing(
            shipmentServices,
            ...(this.editForm.get('shipmentServices')!.value ?? [])
          )
        )
      )
      .subscribe((shipmentServices: IShipmentService[]) => (this.shipmentServicesSharedCollection = shipmentServices));
  }

  protected createFromForm(): IEcomStore {
    return {
      ...new EcomStore(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      nickName: this.editForm.get(['nickName'])!.value,
      apiPassword: this.editForm.get(['apiPassword'])!.value,
      domain: this.editForm.get(['domain'])!.value,
      syncFrequency: this.editForm.get(['syncFrequency'])!.value,
      syncInterval: this.editForm.get(['syncInterval'])!.value,
      active: this.editForm.get(['active'])!.value,
      isManualSync: this.editForm.get(['isManualSync'])!.value,
      createdDate: this.editForm.get(['createdDate'])!.value,
      updatedDate: this.editForm.get(['updatedDate'])!.value,
      ecomStoreAddress: this.editForm.get(['ecomStoreAddress'])!.value,
      ecomStoreColorTheme: this.editForm.get(['ecomStoreColorTheme'])!.value,
      ecomStoreShipmentSettings: this.editForm.get(['ecomStoreShipmentSettings'])!.value,
      ecomStorePackageSettings: this.editForm.get(['ecomStorePackageSettings'])!.value,
      ecomStoreMarkup: this.editForm.get(['ecomStoreMarkup'])!.value,
      company: this.editForm.get(['company'])!.value,
      user: this.editForm.get(['user'])!.value,
      ecomProduct: this.editForm.get(['ecomProduct'])!.value,
      shipmentServices: this.editForm.get(['shipmentServices'])!.value,
    };
  }
}
