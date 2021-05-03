jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomStoreAddressService } from '../service/ecom-store-address.service';
import { IEcomStoreAddress, EcomStoreAddress } from '../ecom-store-address.model';
import { ICountry } from 'app/entities/country/country.model';
import { CountryService } from 'app/entities/country/service/country.service';
import { IProvince } from 'app/entities/province/province.model';
import { ProvinceService } from 'app/entities/province/service/province.service';
import { ICity } from 'app/entities/city/city.model';
import { CityService } from 'app/entities/city/service/city.service';

import { EcomStoreAddressUpdateComponent } from './ecom-store-address-update.component';

describe('Component Tests', () => {
  describe('EcomStoreAddress Management Update Component', () => {
    let comp: EcomStoreAddressUpdateComponent;
    let fixture: ComponentFixture<EcomStoreAddressUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomStoreAddressService: EcomStoreAddressService;
    let countryService: CountryService;
    let provinceService: ProvinceService;
    let cityService: CityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStoreAddressUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomStoreAddressUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreAddressUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomStoreAddressService = TestBed.inject(EcomStoreAddressService);
      countryService = TestBed.inject(CountryService);
      provinceService = TestBed.inject(ProvinceService);
      cityService = TestBed.inject(CityService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call Country query and add missing value', () => {
        const ecomStoreAddress: IEcomStoreAddress = { id: 456 };
        const country: ICountry = { id: 85721 };
        ecomStoreAddress.country = country;

        const countryCollection: ICountry[] = [{ id: 42682 }];
        spyOn(countryService, 'query').and.returnValue(of(new HttpResponse({ body: countryCollection })));
        const additionalCountries = [country];
        const expectedCollection: ICountry[] = [...additionalCountries, ...countryCollection];
        spyOn(countryService, 'addCountryToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStoreAddress });
        comp.ngOnInit();

        expect(countryService.query).toHaveBeenCalled();
        expect(countryService.addCountryToCollectionIfMissing).toHaveBeenCalledWith(countryCollection, ...additionalCountries);
        expect(comp.countriesSharedCollection).toEqual(expectedCollection);
      });

      it('Should call Province query and add missing value', () => {
        const ecomStoreAddress: IEcomStoreAddress = { id: 456 };
        const province: IProvince = { id: 87776 };
        ecomStoreAddress.province = province;

        const provinceCollection: IProvince[] = [{ id: 90414 }];
        spyOn(provinceService, 'query').and.returnValue(of(new HttpResponse({ body: provinceCollection })));
        const additionalProvinces = [province];
        const expectedCollection: IProvince[] = [...additionalProvinces, ...provinceCollection];
        spyOn(provinceService, 'addProvinceToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStoreAddress });
        comp.ngOnInit();

        expect(provinceService.query).toHaveBeenCalled();
        expect(provinceService.addProvinceToCollectionIfMissing).toHaveBeenCalledWith(provinceCollection, ...additionalProvinces);
        expect(comp.provincesSharedCollection).toEqual(expectedCollection);
      });

      it('Should call City query and add missing value', () => {
        const ecomStoreAddress: IEcomStoreAddress = { id: 456 };
        const city: ICity = { id: 22583 };
        ecomStoreAddress.city = city;

        const cityCollection: ICity[] = [{ id: 38194 }];
        spyOn(cityService, 'query').and.returnValue(of(new HttpResponse({ body: cityCollection })));
        const additionalCities = [city];
        const expectedCollection: ICity[] = [...additionalCities, ...cityCollection];
        spyOn(cityService, 'addCityToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStoreAddress });
        comp.ngOnInit();

        expect(cityService.query).toHaveBeenCalled();
        expect(cityService.addCityToCollectionIfMissing).toHaveBeenCalledWith(cityCollection, ...additionalCities);
        expect(comp.citiesSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const ecomStoreAddress: IEcomStoreAddress = { id: 456 };
        const country: ICountry = { id: 45185 };
        ecomStoreAddress.country = country;
        const province: IProvince = { id: 72541 };
        ecomStoreAddress.province = province;
        const city: ICity = { id: 21149 };
        ecomStoreAddress.city = city;

        activatedRoute.data = of({ ecomStoreAddress });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomStoreAddress));
        expect(comp.countriesSharedCollection).toContain(country);
        expect(comp.provincesSharedCollection).toContain(province);
        expect(comp.citiesSharedCollection).toContain(city);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreAddress = { id: 123 };
        spyOn(ecomStoreAddressService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreAddress });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStoreAddress }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomStoreAddressService.update).toHaveBeenCalledWith(ecomStoreAddress);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreAddress = new EcomStoreAddress();
        spyOn(ecomStoreAddressService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreAddress });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStoreAddress }));
        saveSubject.complete();

        // THEN
        expect(ecomStoreAddressService.create).toHaveBeenCalledWith(ecomStoreAddress);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreAddress = { id: 123 };
        spyOn(ecomStoreAddressService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreAddress });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomStoreAddressService.update).toHaveBeenCalledWith(ecomStoreAddress);
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

      describe('trackProvinceById', () => {
        it('Should return tracked Province primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackProvinceById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackCityById', () => {
        it('Should return tracked City primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackCityById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});
