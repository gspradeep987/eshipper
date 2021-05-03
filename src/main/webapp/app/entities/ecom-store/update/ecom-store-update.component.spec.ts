jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomStoreService } from '../service/ecom-store.service';
import { IEcomStore, EcomStore } from '../ecom-store.model';
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

import { EcomStoreUpdateComponent } from './ecom-store-update.component';

describe('Component Tests', () => {
  describe('EcomStore Management Update Component', () => {
    let comp: EcomStoreUpdateComponent;
    let fixture: ComponentFixture<EcomStoreUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomStoreService: EcomStoreService;
    let ecomStoreAddressService: EcomStoreAddressService;
    let ecomStoreColorThemeService: EcomStoreColorThemeService;
    let ecomStoreShipmentSettingsService: EcomStoreShipmentSettingsService;
    let ecomStorePackageSettingsService: EcomStorePackageSettingsService;
    let ecomStoreMarkupService: EcomStoreMarkupService;
    let companyService: CompanyService;
    let userService: UserService;
    let ecomProductService: EcomProductService;
    let shipmentServiceService: ShipmentServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStoreUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomStoreUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomStoreService = TestBed.inject(EcomStoreService);
      ecomStoreAddressService = TestBed.inject(EcomStoreAddressService);
      ecomStoreColorThemeService = TestBed.inject(EcomStoreColorThemeService);
      ecomStoreShipmentSettingsService = TestBed.inject(EcomStoreShipmentSettingsService);
      ecomStorePackageSettingsService = TestBed.inject(EcomStorePackageSettingsService);
      ecomStoreMarkupService = TestBed.inject(EcomStoreMarkupService);
      companyService = TestBed.inject(CompanyService);
      userService = TestBed.inject(UserService);
      ecomProductService = TestBed.inject(EcomProductService);
      shipmentServiceService = TestBed.inject(ShipmentServiceService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call ecomStoreAddress query and add missing value', () => {
        const ecomStore: IEcomStore = { id: 456 };
        const ecomStoreAddress: IEcomStoreAddress = { id: 51142 };
        ecomStore.ecomStoreAddress = ecomStoreAddress;

        const ecomStoreAddressCollection: IEcomStoreAddress[] = [{ id: 81412 }];
        spyOn(ecomStoreAddressService, 'query').and.returnValue(of(new HttpResponse({ body: ecomStoreAddressCollection })));
        const expectedCollection: IEcomStoreAddress[] = [ecomStoreAddress, ...ecomStoreAddressCollection];
        spyOn(ecomStoreAddressService, 'addEcomStoreAddressToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStore });
        comp.ngOnInit();

        expect(ecomStoreAddressService.query).toHaveBeenCalled();
        expect(ecomStoreAddressService.addEcomStoreAddressToCollectionIfMissing).toHaveBeenCalledWith(
          ecomStoreAddressCollection,
          ecomStoreAddress
        );
        expect(comp.ecomStoreAddressesCollection).toEqual(expectedCollection);
      });

      it('Should call ecomStoreColorTheme query and add missing value', () => {
        const ecomStore: IEcomStore = { id: 456 };
        const ecomStoreColorTheme: IEcomStoreColorTheme = { id: 64870 };
        ecomStore.ecomStoreColorTheme = ecomStoreColorTheme;

        const ecomStoreColorThemeCollection: IEcomStoreColorTheme[] = [{ id: 47333 }];
        spyOn(ecomStoreColorThemeService, 'query').and.returnValue(of(new HttpResponse({ body: ecomStoreColorThemeCollection })));
        const expectedCollection: IEcomStoreColorTheme[] = [ecomStoreColorTheme, ...ecomStoreColorThemeCollection];
        spyOn(ecomStoreColorThemeService, 'addEcomStoreColorThemeToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStore });
        comp.ngOnInit();

        expect(ecomStoreColorThemeService.query).toHaveBeenCalled();
        expect(ecomStoreColorThemeService.addEcomStoreColorThemeToCollectionIfMissing).toHaveBeenCalledWith(
          ecomStoreColorThemeCollection,
          ecomStoreColorTheme
        );
        expect(comp.ecomStoreColorThemesCollection).toEqual(expectedCollection);
      });

      it('Should call ecomStoreShipmentSettings query and add missing value', () => {
        const ecomStore: IEcomStore = { id: 456 };
        const ecomStoreShipmentSettings: IEcomStoreShipmentSettings = { id: 28924 };
        ecomStore.ecomStoreShipmentSettings = ecomStoreShipmentSettings;

        const ecomStoreShipmentSettingsCollection: IEcomStoreShipmentSettings[] = [{ id: 63526 }];
        spyOn(ecomStoreShipmentSettingsService, 'query').and.returnValue(
          of(new HttpResponse({ body: ecomStoreShipmentSettingsCollection }))
        );
        const expectedCollection: IEcomStoreShipmentSettings[] = [ecomStoreShipmentSettings, ...ecomStoreShipmentSettingsCollection];
        spyOn(ecomStoreShipmentSettingsService, 'addEcomStoreShipmentSettingsToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStore });
        comp.ngOnInit();

        expect(ecomStoreShipmentSettingsService.query).toHaveBeenCalled();
        expect(ecomStoreShipmentSettingsService.addEcomStoreShipmentSettingsToCollectionIfMissing).toHaveBeenCalledWith(
          ecomStoreShipmentSettingsCollection,
          ecomStoreShipmentSettings
        );
        expect(comp.ecomStoreShipmentSettingsCollection).toEqual(expectedCollection);
      });

      it('Should call ecomStorePackageSettings query and add missing value', () => {
        const ecomStore: IEcomStore = { id: 456 };
        const ecomStorePackageSettings: IEcomStorePackageSettings = { id: 89727 };
        ecomStore.ecomStorePackageSettings = ecomStorePackageSettings;

        const ecomStorePackageSettingsCollection: IEcomStorePackageSettings[] = [{ id: 25716 }];
        spyOn(ecomStorePackageSettingsService, 'query').and.returnValue(of(new HttpResponse({ body: ecomStorePackageSettingsCollection })));
        const expectedCollection: IEcomStorePackageSettings[] = [ecomStorePackageSettings, ...ecomStorePackageSettingsCollection];
        spyOn(ecomStorePackageSettingsService, 'addEcomStorePackageSettingsToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStore });
        comp.ngOnInit();

        expect(ecomStorePackageSettingsService.query).toHaveBeenCalled();
        expect(ecomStorePackageSettingsService.addEcomStorePackageSettingsToCollectionIfMissing).toHaveBeenCalledWith(
          ecomStorePackageSettingsCollection,
          ecomStorePackageSettings
        );
        expect(comp.ecomStorePackageSettingsCollection).toEqual(expectedCollection);
      });

      it('Should call ecomStoreMarkup query and add missing value', () => {
        const ecomStore: IEcomStore = { id: 456 };
        const ecomStoreMarkup: IEcomStoreMarkup = { id: 14352 };
        ecomStore.ecomStoreMarkup = ecomStoreMarkup;

        const ecomStoreMarkupCollection: IEcomStoreMarkup[] = [{ id: 52665 }];
        spyOn(ecomStoreMarkupService, 'query').and.returnValue(of(new HttpResponse({ body: ecomStoreMarkupCollection })));
        const expectedCollection: IEcomStoreMarkup[] = [ecomStoreMarkup, ...ecomStoreMarkupCollection];
        spyOn(ecomStoreMarkupService, 'addEcomStoreMarkupToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStore });
        comp.ngOnInit();

        expect(ecomStoreMarkupService.query).toHaveBeenCalled();
        expect(ecomStoreMarkupService.addEcomStoreMarkupToCollectionIfMissing).toHaveBeenCalledWith(
          ecomStoreMarkupCollection,
          ecomStoreMarkup
        );
        expect(comp.ecomStoreMarkupsCollection).toEqual(expectedCollection);
      });

      it('Should call Company query and add missing value', () => {
        const ecomStore: IEcomStore = { id: 456 };
        const company: ICompany = { id: 30933 };
        ecomStore.company = company;

        const companyCollection: ICompany[] = [{ id: 45992 }];
        spyOn(companyService, 'query').and.returnValue(of(new HttpResponse({ body: companyCollection })));
        const additionalCompanies = [company];
        const expectedCollection: ICompany[] = [...additionalCompanies, ...companyCollection];
        spyOn(companyService, 'addCompanyToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStore });
        comp.ngOnInit();

        expect(companyService.query).toHaveBeenCalled();
        expect(companyService.addCompanyToCollectionIfMissing).toHaveBeenCalledWith(companyCollection, ...additionalCompanies);
        expect(comp.companiesSharedCollection).toEqual(expectedCollection);
      });

      it('Should call User query and add missing value', () => {
        const ecomStore: IEcomStore = { id: 456 };
        const user: IUser = { id: 27699 };
        ecomStore.user = user;

        const userCollection: IUser[] = [{ id: 87926 }];
        spyOn(userService, 'query').and.returnValue(of(new HttpResponse({ body: userCollection })));
        const additionalUsers = [user];
        const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
        spyOn(userService, 'addUserToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStore });
        comp.ngOnInit();

        expect(userService.query).toHaveBeenCalled();
        expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(userCollection, ...additionalUsers);
        expect(comp.usersSharedCollection).toEqual(expectedCollection);
      });

      it('Should call EcomProduct query and add missing value', () => {
        const ecomStore: IEcomStore = { id: 456 };
        const ecomProduct: IEcomProduct = { id: 66974 };
        ecomStore.ecomProduct = ecomProduct;

        const ecomProductCollection: IEcomProduct[] = [{ id: 73945 }];
        spyOn(ecomProductService, 'query').and.returnValue(of(new HttpResponse({ body: ecomProductCollection })));
        const additionalEcomProducts = [ecomProduct];
        const expectedCollection: IEcomProduct[] = [...additionalEcomProducts, ...ecomProductCollection];
        spyOn(ecomProductService, 'addEcomProductToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStore });
        comp.ngOnInit();

        expect(ecomProductService.query).toHaveBeenCalled();
        expect(ecomProductService.addEcomProductToCollectionIfMissing).toHaveBeenCalledWith(
          ecomProductCollection,
          ...additionalEcomProducts
        );
        expect(comp.ecomProductsSharedCollection).toEqual(expectedCollection);
      });

      it('Should call ShipmentService query and add missing value', () => {
        const ecomStore: IEcomStore = { id: 456 };
        const shipmentServices: IShipmentService[] = [{ id: 62568 }];
        ecomStore.shipmentServices = shipmentServices;

        const shipmentServiceCollection: IShipmentService[] = [{ id: 56773 }];
        spyOn(shipmentServiceService, 'query').and.returnValue(of(new HttpResponse({ body: shipmentServiceCollection })));
        const additionalShipmentServices = [...shipmentServices];
        const expectedCollection: IShipmentService[] = [...additionalShipmentServices, ...shipmentServiceCollection];
        spyOn(shipmentServiceService, 'addShipmentServiceToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStore });
        comp.ngOnInit();

        expect(shipmentServiceService.query).toHaveBeenCalled();
        expect(shipmentServiceService.addShipmentServiceToCollectionIfMissing).toHaveBeenCalledWith(
          shipmentServiceCollection,
          ...additionalShipmentServices
        );
        expect(comp.shipmentServicesSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const ecomStore: IEcomStore = { id: 456 };
        const ecomStoreAddress: IEcomStoreAddress = { id: 5414 };
        ecomStore.ecomStoreAddress = ecomStoreAddress;
        const ecomStoreColorTheme: IEcomStoreColorTheme = { id: 37754 };
        ecomStore.ecomStoreColorTheme = ecomStoreColorTheme;
        const ecomStoreShipmentSettings: IEcomStoreShipmentSettings = { id: 41076 };
        ecomStore.ecomStoreShipmentSettings = ecomStoreShipmentSettings;
        const ecomStorePackageSettings: IEcomStorePackageSettings = { id: 81091 };
        ecomStore.ecomStorePackageSettings = ecomStorePackageSettings;
        const ecomStoreMarkup: IEcomStoreMarkup = { id: 32872 };
        ecomStore.ecomStoreMarkup = ecomStoreMarkup;
        const company: ICompany = { id: 38121 };
        ecomStore.company = company;
        const user: IUser = { id: 47918 };
        ecomStore.user = user;
        const ecomProduct: IEcomProduct = { id: 13992 };
        ecomStore.ecomProduct = ecomProduct;
        const shipmentServices: IShipmentService = { id: 16972 };
        ecomStore.shipmentServices = [shipmentServices];

        activatedRoute.data = of({ ecomStore });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomStore));
        expect(comp.ecomStoreAddressesCollection).toContain(ecomStoreAddress);
        expect(comp.ecomStoreColorThemesCollection).toContain(ecomStoreColorTheme);
        expect(comp.ecomStoreShipmentSettingsCollection).toContain(ecomStoreShipmentSettings);
        expect(comp.ecomStorePackageSettingsCollection).toContain(ecomStorePackageSettings);
        expect(comp.ecomStoreMarkupsCollection).toContain(ecomStoreMarkup);
        expect(comp.companiesSharedCollection).toContain(company);
        expect(comp.usersSharedCollection).toContain(user);
        expect(comp.ecomProductsSharedCollection).toContain(ecomProduct);
        expect(comp.shipmentServicesSharedCollection).toContain(shipmentServices);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStore = { id: 123 };
        spyOn(ecomStoreService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStore });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStore }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomStoreService.update).toHaveBeenCalledWith(ecomStore);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStore = new EcomStore();
        spyOn(ecomStoreService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStore });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStore }));
        saveSubject.complete();

        // THEN
        expect(ecomStoreService.create).toHaveBeenCalledWith(ecomStore);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStore = { id: 123 };
        spyOn(ecomStoreService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStore });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomStoreService.update).toHaveBeenCalledWith(ecomStore);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackEcomStoreAddressById', () => {
        it('Should return tracked EcomStoreAddress primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomStoreAddressById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackEcomStoreColorThemeById', () => {
        it('Should return tracked EcomStoreColorTheme primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomStoreColorThemeById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackEcomStoreShipmentSettingsById', () => {
        it('Should return tracked EcomStoreShipmentSettings primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomStoreShipmentSettingsById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackEcomStorePackageSettingsById', () => {
        it('Should return tracked EcomStorePackageSettings primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomStorePackageSettingsById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackEcomStoreMarkupById', () => {
        it('Should return tracked EcomStoreMarkup primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomStoreMarkupById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackCompanyById', () => {
        it('Should return tracked Company primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackCompanyById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackUserById', () => {
        it('Should return tracked User primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackUserById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackEcomProductById', () => {
        it('Should return tracked EcomProduct primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomProductById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackShipmentServiceById', () => {
        it('Should return tracked ShipmentService primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackShipmentServiceById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });

    describe('Getting selected relationships', () => {
      describe('getSelectedShipmentService', () => {
        it('Should return option if no ShipmentService is selected', () => {
          const option = { id: 123 };
          const result = comp.getSelectedShipmentService(option);
          expect(result === option).toEqual(true);
        });

        it('Should return selected ShipmentService for according option', () => {
          const option = { id: 123 };
          const selected = { id: 123 };
          const selected2 = { id: 456 };
          const result = comp.getSelectedShipmentService(option, [selected2, selected]);
          expect(result === selected).toEqual(true);
          expect(result === selected2).toEqual(false);
          expect(result === option).toEqual(false);
        });

        it('Should return option if this ShipmentService is not selected', () => {
          const option = { id: 123 };
          const selected = { id: 456 };
          const result = comp.getSelectedShipmentService(option, [selected]);
          expect(result === option).toEqual(true);
          expect(result === selected).toEqual(false);
        });
      });
    });
  });
});
