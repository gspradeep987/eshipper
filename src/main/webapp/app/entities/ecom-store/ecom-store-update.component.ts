import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEcomStore, EcomStore } from 'app/shared/model/ecom-store.model';
import { EcomStoreService } from './ecom-store.service';
import { IEcomStoreAddress } from 'app/shared/model/ecom-store-address.model';
import { EcomStoreAddressService } from 'app/entities/ecom-store-address/ecom-store-address.service';
import { IEcomStoreColorTheme } from 'app/shared/model/ecom-store-color-theme.model';
import { EcomStoreColorThemeService } from 'app/entities/ecom-store-color-theme/ecom-store-color-theme.service';
import { IEcomStoreShipmentSettings } from 'app/shared/model/ecom-store-shipment-settings.model';
import { EcomStoreShipmentSettingsService } from 'app/entities/ecom-store-shipment-settings/ecom-store-shipment-settings.service';
import { IEcomStorePackageSettings } from 'app/shared/model/ecom-store-package-settings.model';
import { EcomStorePackageSettingsService } from 'app/entities/ecom-store-package-settings/ecom-store-package-settings.service';
import { IEcomStoreMarkup } from 'app/shared/model/ecom-store-markup.model';
import { EcomStoreMarkupService } from 'app/entities/ecom-store-markup/ecom-store-markup.service';
import { ICompany } from 'app/shared/model/company.model';
import { CompanyService } from 'app/entities/company/company.service';
import { IUser } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { IEcomProduct } from 'app/shared/model/ecom-product.model';
import { EcomProductService } from 'app/entities/ecom-product/ecom-product.service';
import { IShipmentService } from 'app/shared/model/shipment-service.model';
import { ShipmentServiceService } from 'app/entities/shipment-service/shipment-service.service';

type SelectableEntity =
  | IEcomStoreAddress
  | IEcomStoreColorTheme
  | IEcomStoreShipmentSettings
  | IEcomStorePackageSettings
  | IEcomStoreMarkup
  | ICompany
  | IUser
  | IEcomProduct
  | IShipmentService;

@Component({
  selector: 'jhi-ecom-store-update',
  templateUrl: './ecom-store-update.component.html'
})
export class EcomStoreUpdateComponent implements OnInit {
  isSaving = false;
  ecomstoreaddresses: IEcomStoreAddress[] = [];
  ecomstorecolorthemes: IEcomStoreColorTheme[] = [];
  ecomstoreshipmentsettings: IEcomStoreShipmentSettings[] = [];
  ecomstorepackagesettings: IEcomStorePackageSettings[] = [];
  ecomstoremarkups: IEcomStoreMarkup[] = [];
  companies: ICompany[] = [];
  users: IUser[] = [];
  ecomproducts: IEcomProduct[] = [];
  shipmentservices: IShipmentService[] = [];
  createdDateDp: any;
  updatedDateDp: any;

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
    ecomStoreAddressId: [],
    ecomStoreColorThemeId: [],
    ecomStoreShipmentSettingsId: [],
    ecomStorePackageSettingsId: [],
    ecomStoreMarkupId: [],
    companyId: [],
    userId: [],
    ecomProductId: [],
    shipmentServices: []
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
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ecomStore }) => {
      this.updateForm(ecomStore);

      this.ecomStoreAddressService
        .query({ filter: 'ecomstore-is-null' })
        .pipe(
          map((res: HttpResponse<IEcomStoreAddress[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IEcomStoreAddress[]) => {
          if (!ecomStore.ecomStoreAddressId) {
            this.ecomstoreaddresses = resBody;
          } else {
            this.ecomStoreAddressService
              .find(ecomStore.ecomStoreAddressId)
              .pipe(
                map((subRes: HttpResponse<IEcomStoreAddress>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IEcomStoreAddress[]) => (this.ecomstoreaddresses = concatRes));
          }
        });

      this.ecomStoreColorThemeService
        .query({ filter: 'ecomstore-is-null' })
        .pipe(
          map((res: HttpResponse<IEcomStoreColorTheme[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IEcomStoreColorTheme[]) => {
          if (!ecomStore.ecomStoreColorThemeId) {
            this.ecomstorecolorthemes = resBody;
          } else {
            this.ecomStoreColorThemeService
              .find(ecomStore.ecomStoreColorThemeId)
              .pipe(
                map((subRes: HttpResponse<IEcomStoreColorTheme>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IEcomStoreColorTheme[]) => (this.ecomstorecolorthemes = concatRes));
          }
        });

      this.ecomStoreShipmentSettingsService
        .query({ filter: 'ecomstore-is-null' })
        .pipe(
          map((res: HttpResponse<IEcomStoreShipmentSettings[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IEcomStoreShipmentSettings[]) => {
          if (!ecomStore.ecomStoreShipmentSettingsId) {
            this.ecomstoreshipmentsettings = resBody;
          } else {
            this.ecomStoreShipmentSettingsService
              .find(ecomStore.ecomStoreShipmentSettingsId)
              .pipe(
                map((subRes: HttpResponse<IEcomStoreShipmentSettings>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IEcomStoreShipmentSettings[]) => (this.ecomstoreshipmentsettings = concatRes));
          }
        });

      this.ecomStorePackageSettingsService
        .query({ filter: 'ecomstore-is-null' })
        .pipe(
          map((res: HttpResponse<IEcomStorePackageSettings[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IEcomStorePackageSettings[]) => {
          if (!ecomStore.ecomStorePackageSettingsId) {
            this.ecomstorepackagesettings = resBody;
          } else {
            this.ecomStorePackageSettingsService
              .find(ecomStore.ecomStorePackageSettingsId)
              .pipe(
                map((subRes: HttpResponse<IEcomStorePackageSettings>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IEcomStorePackageSettings[]) => (this.ecomstorepackagesettings = concatRes));
          }
        });

      this.ecomStoreMarkupService
        .query({ filter: 'ecomstore-is-null' })
        .pipe(
          map((res: HttpResponse<IEcomStoreMarkup[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IEcomStoreMarkup[]) => {
          if (!ecomStore.ecomStoreMarkupId) {
            this.ecomstoremarkups = resBody;
          } else {
            this.ecomStoreMarkupService
              .find(ecomStore.ecomStoreMarkupId)
              .pipe(
                map((subRes: HttpResponse<IEcomStoreMarkup>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IEcomStoreMarkup[]) => (this.ecomstoremarkups = concatRes));
          }
        });

      this.companyService.query().subscribe((res: HttpResponse<ICompany[]>) => (this.companies = res.body || []));

      this.userService.query().subscribe((res: HttpResponse<IUser[]>) => (this.users = res.body || []));

      this.ecomProductService.query().subscribe((res: HttpResponse<IEcomProduct[]>) => (this.ecomproducts = res.body || []));

      this.shipmentServiceService.query().subscribe((res: HttpResponse<IShipmentService[]>) => (this.shipmentservices = res.body || []));
    });
  }

  updateForm(ecomStore: IEcomStore): void {
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
      ecomStoreAddressId: ecomStore.ecomStoreAddressId,
      ecomStoreColorThemeId: ecomStore.ecomStoreColorThemeId,
      ecomStoreShipmentSettingsId: ecomStore.ecomStoreShipmentSettingsId,
      ecomStorePackageSettingsId: ecomStore.ecomStorePackageSettingsId,
      ecomStoreMarkupId: ecomStore.ecomStoreMarkupId,
      companyId: ecomStore.companyId,
      userId: ecomStore.userId,
      ecomProductId: ecomStore.ecomProductId,
      shipmentServices: ecomStore.shipmentServices
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

  private createFromForm(): IEcomStore {
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
      ecomStoreAddressId: this.editForm.get(['ecomStoreAddressId'])!.value,
      ecomStoreColorThemeId: this.editForm.get(['ecomStoreColorThemeId'])!.value,
      ecomStoreShipmentSettingsId: this.editForm.get(['ecomStoreShipmentSettingsId'])!.value,
      ecomStorePackageSettingsId: this.editForm.get(['ecomStorePackageSettingsId'])!.value,
      ecomStoreMarkupId: this.editForm.get(['ecomStoreMarkupId'])!.value,
      companyId: this.editForm.get(['companyId'])!.value,
      userId: this.editForm.get(['userId'])!.value,
      ecomProductId: this.editForm.get(['ecomProductId'])!.value,
      shipmentServices: this.editForm.get(['shipmentServices'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEcomStore>>): void {
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

  getSelected(selectedVals: IShipmentService[], option: IShipmentService): IShipmentService {
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
