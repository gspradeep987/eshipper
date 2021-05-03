jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomProductService } from '../service/ecom-product.service';
import { IEcomProduct, EcomProduct } from '../ecom-product.model';
import { ICountry } from 'app/entities/country/country.model';
import { CountryService } from 'app/entities/country/service/country.service';
import { IEcomWarehouse } from 'app/entities/ecom-warehouse/ecom-warehouse.model';
import { EcomWarehouseService } from 'app/entities/ecom-warehouse/service/ecom-warehouse.service';
import { IEcomOrder } from 'app/entities/ecom-order/ecom-order.model';
import { EcomOrderService } from 'app/entities/ecom-order/service/ecom-order.service';

import { EcomProductUpdateComponent } from './ecom-product-update.component';

describe('Component Tests', () => {
  describe('EcomProduct Management Update Component', () => {
    let comp: EcomProductUpdateComponent;
    let fixture: ComponentFixture<EcomProductUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomProductService: EcomProductService;
    let countryService: CountryService;
    let ecomWarehouseService: EcomWarehouseService;
    let ecomOrderService: EcomOrderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomProductUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomProductUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomProductUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomProductService = TestBed.inject(EcomProductService);
      countryService = TestBed.inject(CountryService);
      ecomWarehouseService = TestBed.inject(EcomWarehouseService);
      ecomOrderService = TestBed.inject(EcomOrderService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call Country query and add missing value', () => {
        const ecomProduct: IEcomProduct = { id: 456 };
        const country: ICountry = { id: 94597 };
        ecomProduct.country = country;

        const countryCollection: ICountry[] = [{ id: 7467 }];
        spyOn(countryService, 'query').and.returnValue(of(new HttpResponse({ body: countryCollection })));
        const additionalCountries = [country];
        const expectedCollection: ICountry[] = [...additionalCountries, ...countryCollection];
        spyOn(countryService, 'addCountryToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomProduct });
        comp.ngOnInit();

        expect(countryService.query).toHaveBeenCalled();
        expect(countryService.addCountryToCollectionIfMissing).toHaveBeenCalledWith(countryCollection, ...additionalCountries);
        expect(comp.countriesSharedCollection).toEqual(expectedCollection);
      });

      it('Should call EcomWarehouse query and add missing value', () => {
        const ecomProduct: IEcomProduct = { id: 456 };
        const ecomWarehouses: IEcomWarehouse[] = [{ id: 67485 }];
        ecomProduct.ecomWarehouses = ecomWarehouses;

        const ecomWarehouseCollection: IEcomWarehouse[] = [{ id: 36819 }];
        spyOn(ecomWarehouseService, 'query').and.returnValue(of(new HttpResponse({ body: ecomWarehouseCollection })));
        const additionalEcomWarehouses = [...ecomWarehouses];
        const expectedCollection: IEcomWarehouse[] = [...additionalEcomWarehouses, ...ecomWarehouseCollection];
        spyOn(ecomWarehouseService, 'addEcomWarehouseToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomProduct });
        comp.ngOnInit();

        expect(ecomWarehouseService.query).toHaveBeenCalled();
        expect(ecomWarehouseService.addEcomWarehouseToCollectionIfMissing).toHaveBeenCalledWith(
          ecomWarehouseCollection,
          ...additionalEcomWarehouses
        );
        expect(comp.ecomWarehousesSharedCollection).toEqual(expectedCollection);
      });

      it('Should call EcomOrder query and add missing value', () => {
        const ecomProduct: IEcomProduct = { id: 456 };
        const ecomOrder: IEcomOrder = { id: 95356 };
        ecomProduct.ecomOrder = ecomOrder;

        const ecomOrderCollection: IEcomOrder[] = [{ id: 95441 }];
        spyOn(ecomOrderService, 'query').and.returnValue(of(new HttpResponse({ body: ecomOrderCollection })));
        const additionalEcomOrders = [ecomOrder];
        const expectedCollection: IEcomOrder[] = [...additionalEcomOrders, ...ecomOrderCollection];
        spyOn(ecomOrderService, 'addEcomOrderToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomProduct });
        comp.ngOnInit();

        expect(ecomOrderService.query).toHaveBeenCalled();
        expect(ecomOrderService.addEcomOrderToCollectionIfMissing).toHaveBeenCalledWith(ecomOrderCollection, ...additionalEcomOrders);
        expect(comp.ecomOrdersSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const ecomProduct: IEcomProduct = { id: 456 };
        const country: ICountry = { id: 92718 };
        ecomProduct.country = country;
        const ecomWarehouses: IEcomWarehouse = { id: 53193 };
        ecomProduct.ecomWarehouses = [ecomWarehouses];
        const ecomOrder: IEcomOrder = { id: 71852 };
        ecomProduct.ecomOrder = ecomOrder;

        activatedRoute.data = of({ ecomProduct });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomProduct));
        expect(comp.countriesSharedCollection).toContain(country);
        expect(comp.ecomWarehousesSharedCollection).toContain(ecomWarehouses);
        expect(comp.ecomOrdersSharedCollection).toContain(ecomOrder);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomProduct = { id: 123 };
        spyOn(ecomProductService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomProduct });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomProduct }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomProductService.update).toHaveBeenCalledWith(ecomProduct);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomProduct = new EcomProduct();
        spyOn(ecomProductService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomProduct });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomProduct }));
        saveSubject.complete();

        // THEN
        expect(ecomProductService.create).toHaveBeenCalledWith(ecomProduct);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomProduct = { id: 123 };
        spyOn(ecomProductService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomProduct });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomProductService.update).toHaveBeenCalledWith(ecomProduct);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackCountryById', () => {
        it('Should return tracked Country primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackCountryById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackEcomWarehouseById', () => {
        it('Should return tracked EcomWarehouse primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomWarehouseById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackEcomOrderById', () => {
        it('Should return tracked EcomOrder primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomOrderById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });

    describe('Getting selected relationships', () => {
      describe('getSelectedEcomWarehouse', () => {
        it('Should return option if no EcomWarehouse is selected', () => {
          const option = { id: 123 };
          const result = comp.getSelectedEcomWarehouse(option);
          expect(result === option).toEqual(true);
        });

        it('Should return selected EcomWarehouse for according option', () => {
          const option = { id: 123 };
          const selected = { id: 123 };
          const selected2 = { id: 456 };
          const result = comp.getSelectedEcomWarehouse(option, [selected2, selected]);
          expect(result === selected).toEqual(true);
          expect(result === selected2).toEqual(false);
          expect(result === option).toEqual(false);
        });

        it('Should return option if this EcomWarehouse is not selected', () => {
          const option = { id: 123 };
          const selected = { id: 456 };
          const result = comp.getSelectedEcomWarehouse(option, [selected]);
          expect(result === option).toEqual(true);
          expect(result === selected).toEqual(false);
        });
      });
    });
  });
});
