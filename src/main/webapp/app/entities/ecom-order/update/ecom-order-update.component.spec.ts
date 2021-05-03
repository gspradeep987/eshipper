jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomOrderService } from '../service/ecom-order.service';
import { IEcomOrder, EcomOrder } from '../ecom-order.model';
import { ICurrency } from 'app/entities/currency/currency.model';
import { CurrencyService } from 'app/entities/currency/service/currency.service';
import { IShippingAddress } from 'app/entities/shipping-address/shipping-address.model';
import { ShippingAddressService } from 'app/entities/shipping-address/service/shipping-address.service';
import { IEcomStore } from 'app/entities/ecom-store/ecom-store.model';
import { EcomStoreService } from 'app/entities/ecom-store/service/ecom-store.service';

import { EcomOrderUpdateComponent } from './ecom-order-update.component';

describe('Component Tests', () => {
  describe('EcomOrder Management Update Component', () => {
    let comp: EcomOrderUpdateComponent;
    let fixture: ComponentFixture<EcomOrderUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomOrderService: EcomOrderService;
    let currencyService: CurrencyService;
    let shippingAddressService: ShippingAddressService;
    let ecomStoreService: EcomStoreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomOrderUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomOrderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomOrderUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomOrderService = TestBed.inject(EcomOrderService);
      currencyService = TestBed.inject(CurrencyService);
      shippingAddressService = TestBed.inject(ShippingAddressService);
      ecomStoreService = TestBed.inject(EcomStoreService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call Currency query and add missing value', () => {
        const ecomOrder: IEcomOrder = { id: 456 };
        const currency: ICurrency = { id: 78948 };
        ecomOrder.currency = currency;

        const currencyCollection: ICurrency[] = [{ id: 43534 }];
        spyOn(currencyService, 'query').and.returnValue(of(new HttpResponse({ body: currencyCollection })));
        const additionalCurrencies = [currency];
        const expectedCollection: ICurrency[] = [...additionalCurrencies, ...currencyCollection];
        spyOn(currencyService, 'addCurrencyToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomOrder });
        comp.ngOnInit();

        expect(currencyService.query).toHaveBeenCalled();
        expect(currencyService.addCurrencyToCollectionIfMissing).toHaveBeenCalledWith(currencyCollection, ...additionalCurrencies);
        expect(comp.currenciesSharedCollection).toEqual(expectedCollection);
      });

      it('Should call ShippingAddress query and add missing value', () => {
        const ecomOrder: IEcomOrder = { id: 456 };
        const shippingAddress: IShippingAddress = { id: 59233 };
        ecomOrder.shippingAddress = shippingAddress;
        const billingAddress: IShippingAddress = { id: 67870 };
        ecomOrder.billingAddress = billingAddress;

        const shippingAddressCollection: IShippingAddress[] = [{ id: 46769 }];
        spyOn(shippingAddressService, 'query').and.returnValue(of(new HttpResponse({ body: shippingAddressCollection })));
        const additionalShippingAddresses = [shippingAddress, billingAddress];
        const expectedCollection: IShippingAddress[] = [...additionalShippingAddresses, ...shippingAddressCollection];
        spyOn(shippingAddressService, 'addShippingAddressToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomOrder });
        comp.ngOnInit();

        expect(shippingAddressService.query).toHaveBeenCalled();
        expect(shippingAddressService.addShippingAddressToCollectionIfMissing).toHaveBeenCalledWith(
          shippingAddressCollection,
          ...additionalShippingAddresses
        );
        expect(comp.shippingAddressesSharedCollection).toEqual(expectedCollection);
      });

      it('Should call EcomStore query and add missing value', () => {
        const ecomOrder: IEcomOrder = { id: 456 };
        const ecomStore: IEcomStore = { id: 25701 };
        ecomOrder.ecomStore = ecomStore;

        const ecomStoreCollection: IEcomStore[] = [{ id: 10475 }];
        spyOn(ecomStoreService, 'query').and.returnValue(of(new HttpResponse({ body: ecomStoreCollection })));
        const additionalEcomStores = [ecomStore];
        const expectedCollection: IEcomStore[] = [...additionalEcomStores, ...ecomStoreCollection];
        spyOn(ecomStoreService, 'addEcomStoreToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomOrder });
        comp.ngOnInit();

        expect(ecomStoreService.query).toHaveBeenCalled();
        expect(ecomStoreService.addEcomStoreToCollectionIfMissing).toHaveBeenCalledWith(ecomStoreCollection, ...additionalEcomStores);
        expect(comp.ecomStoresSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const ecomOrder: IEcomOrder = { id: 456 };
        const currency: ICurrency = { id: 54564 };
        ecomOrder.currency = currency;
        const shippingAddress: IShippingAddress = { id: 3689 };
        ecomOrder.shippingAddress = shippingAddress;
        const billingAddress: IShippingAddress = { id: 60652 };
        ecomOrder.billingAddress = billingAddress;
        const ecomStore: IEcomStore = { id: 22594 };
        ecomOrder.ecomStore = ecomStore;

        activatedRoute.data = of({ ecomOrder });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomOrder));
        expect(comp.currenciesSharedCollection).toContain(currency);
        expect(comp.shippingAddressesSharedCollection).toContain(shippingAddress);
        expect(comp.shippingAddressesSharedCollection).toContain(billingAddress);
        expect(comp.ecomStoresSharedCollection).toContain(ecomStore);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomOrder = { id: 123 };
        spyOn(ecomOrderService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomOrder });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomOrder }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomOrderService.update).toHaveBeenCalledWith(ecomOrder);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomOrder = new EcomOrder();
        spyOn(ecomOrderService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomOrder });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomOrder }));
        saveSubject.complete();

        // THEN
        expect(ecomOrderService.create).toHaveBeenCalledWith(ecomOrder);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomOrder = { id: 123 };
        spyOn(ecomOrderService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomOrder });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomOrderService.update).toHaveBeenCalledWith(ecomOrder);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackCurrencyById', () => {
        it('Should return tracked Currency primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackCurrencyById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackShippingAddressById', () => {
        it('Should return tracked ShippingAddress primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackShippingAddressById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackEcomStoreById', () => {
        it('Should return tracked EcomStore primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomStoreById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});
