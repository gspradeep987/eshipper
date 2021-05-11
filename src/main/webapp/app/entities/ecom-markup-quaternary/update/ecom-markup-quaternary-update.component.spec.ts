jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomMarkupQuaternaryService } from '../service/ecom-markup-quaternary.service';
import { IEcomMarkupQuaternary, EcomMarkupQuaternary } from '../ecom-markup-quaternary.model';
import { ICountry } from 'app/entities/country/country.model';
import { CountryService } from 'app/entities/country/service/country.service';

import { EcomMarkupQuaternaryUpdateComponent } from './ecom-markup-quaternary-update.component';

describe('Component Tests', () => {
  describe('EcomMarkupQuaternary Management Update Component', () => {
    let comp: EcomMarkupQuaternaryUpdateComponent;
    let fixture: ComponentFixture<EcomMarkupQuaternaryUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomMarkupQuaternaryService: EcomMarkupQuaternaryService;
    let countryService: CountryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomMarkupQuaternaryUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomMarkupQuaternaryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupQuaternaryUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomMarkupQuaternaryService = TestBed.inject(EcomMarkupQuaternaryService);
      countryService = TestBed.inject(CountryService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call Country query and add missing value', () => {
        const ecomMarkupQuaternary: IEcomMarkupQuaternary = { id: 456 };
        const country: ICountry = { id: 82050 };
        ecomMarkupQuaternary.country = country;

        const countryCollection: ICountry[] = [{ id: 5164 }];
        spyOn(countryService, 'query').and.returnValue(of(new HttpResponse({ body: countryCollection })));
        const additionalCountries = [country];
        const expectedCollection: ICountry[] = [...additionalCountries, ...countryCollection];
        spyOn(countryService, 'addCountryToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomMarkupQuaternary });
        comp.ngOnInit();

        expect(countryService.query).toHaveBeenCalled();
        expect(countryService.addCountryToCollectionIfMissing).toHaveBeenCalledWith(countryCollection, ...additionalCountries);
        expect(comp.countriesSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const ecomMarkupQuaternary: IEcomMarkupQuaternary = { id: 456 };
        const country: ICountry = { id: 93723 };
        ecomMarkupQuaternary.country = country;

        activatedRoute.data = of({ ecomMarkupQuaternary });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomMarkupQuaternary));
        expect(comp.countriesSharedCollection).toContain(country);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMarkupQuaternary = { id: 123 };
        spyOn(ecomMarkupQuaternaryService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMarkupQuaternary });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomMarkupQuaternary }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomMarkupQuaternaryService.update).toHaveBeenCalledWith(ecomMarkupQuaternary);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMarkupQuaternary = new EcomMarkupQuaternary();
        spyOn(ecomMarkupQuaternaryService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMarkupQuaternary });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomMarkupQuaternary }));
        saveSubject.complete();

        // THEN
        expect(ecomMarkupQuaternaryService.create).toHaveBeenCalledWith(ecomMarkupQuaternary);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMarkupQuaternary = { id: 123 };
        spyOn(ecomMarkupQuaternaryService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMarkupQuaternary });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomMarkupQuaternaryService.update).toHaveBeenCalledWith(ecomMarkupQuaternary);
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
    });
  });
});
