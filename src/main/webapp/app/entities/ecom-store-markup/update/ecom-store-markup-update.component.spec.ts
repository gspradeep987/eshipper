jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomStoreMarkupService } from '../service/ecom-store-markup.service';
import { IEcomStoreMarkup, EcomStoreMarkup } from '../ecom-store-markup.model';
import { IEcomMarkupPrimary } from 'app/entities/ecom-markup-primary/ecom-markup-primary.model';
import { EcomMarkupPrimaryService } from 'app/entities/ecom-markup-primary/service/ecom-markup-primary.service';
import { IEcomMarkupSecondary } from 'app/entities/ecom-markup-secondary/ecom-markup-secondary.model';
import { EcomMarkupSecondaryService } from 'app/entities/ecom-markup-secondary/service/ecom-markup-secondary.service';
import { IEcomMarkupTertiary } from 'app/entities/ecom-markup-tertiary/ecom-markup-tertiary.model';
import { EcomMarkupTertiaryService } from 'app/entities/ecom-markup-tertiary/service/ecom-markup-tertiary.service';
import { IEcomMarkupQuaternary } from 'app/entities/ecom-markup-quaternary/ecom-markup-quaternary.model';
import { EcomMarkupQuaternaryService } from 'app/entities/ecom-markup-quaternary/service/ecom-markup-quaternary.service';

import { EcomStoreMarkupUpdateComponent } from './ecom-store-markup-update.component';

describe('Component Tests', () => {
  describe('EcomStoreMarkup Management Update Component', () => {
    let comp: EcomStoreMarkupUpdateComponent;
    let fixture: ComponentFixture<EcomStoreMarkupUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomStoreMarkupService: EcomStoreMarkupService;
    let ecomMarkupPrimaryService: EcomMarkupPrimaryService;
    let ecomMarkupSecondaryService: EcomMarkupSecondaryService;
    let ecomMarkupTertiaryService: EcomMarkupTertiaryService;
    let ecomMarkupQuaternaryService: EcomMarkupQuaternaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStoreMarkupUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomStoreMarkupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreMarkupUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomStoreMarkupService = TestBed.inject(EcomStoreMarkupService);
      ecomMarkupPrimaryService = TestBed.inject(EcomMarkupPrimaryService);
      ecomMarkupSecondaryService = TestBed.inject(EcomMarkupSecondaryService);
      ecomMarkupTertiaryService = TestBed.inject(EcomMarkupTertiaryService);
      ecomMarkupQuaternaryService = TestBed.inject(EcomMarkupQuaternaryService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call ecomMarkupPrimary query and add missing value', () => {
        const ecomStoreMarkup: IEcomStoreMarkup = { id: 456 };
        const ecomMarkupPrimary: IEcomMarkupPrimary = { id: 60537 };
        ecomStoreMarkup.ecomMarkupPrimary = ecomMarkupPrimary;

        const ecomMarkupPrimaryCollection: IEcomMarkupPrimary[] = [{ id: 32925 }];
        spyOn(ecomMarkupPrimaryService, 'query').and.returnValue(of(new HttpResponse({ body: ecomMarkupPrimaryCollection })));
        const expectedCollection: IEcomMarkupPrimary[] = [ecomMarkupPrimary, ...ecomMarkupPrimaryCollection];
        spyOn(ecomMarkupPrimaryService, 'addEcomMarkupPrimaryToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStoreMarkup });
        comp.ngOnInit();

        expect(ecomMarkupPrimaryService.query).toHaveBeenCalled();
        expect(ecomMarkupPrimaryService.addEcomMarkupPrimaryToCollectionIfMissing).toHaveBeenCalledWith(
          ecomMarkupPrimaryCollection,
          ecomMarkupPrimary
        );
        expect(comp.ecomMarkupPrimariesCollection).toEqual(expectedCollection);
      });

      it('Should call ecomMarkupSecondary query and add missing value', () => {
        const ecomStoreMarkup: IEcomStoreMarkup = { id: 456 };
        const ecomMarkupSecondary: IEcomMarkupSecondary = { id: 58983 };
        ecomStoreMarkup.ecomMarkupSecondary = ecomMarkupSecondary;

        const ecomMarkupSecondaryCollection: IEcomMarkupSecondary[] = [{ id: 95230 }];
        spyOn(ecomMarkupSecondaryService, 'query').and.returnValue(of(new HttpResponse({ body: ecomMarkupSecondaryCollection })));
        const expectedCollection: IEcomMarkupSecondary[] = [ecomMarkupSecondary, ...ecomMarkupSecondaryCollection];
        spyOn(ecomMarkupSecondaryService, 'addEcomMarkupSecondaryToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStoreMarkup });
        comp.ngOnInit();

        expect(ecomMarkupSecondaryService.query).toHaveBeenCalled();
        expect(ecomMarkupSecondaryService.addEcomMarkupSecondaryToCollectionIfMissing).toHaveBeenCalledWith(
          ecomMarkupSecondaryCollection,
          ecomMarkupSecondary
        );
        expect(comp.ecomMarkupSecondariesCollection).toEqual(expectedCollection);
      });

      it('Should call ecomMarkupTertiary query and add missing value', () => {
        const ecomStoreMarkup: IEcomStoreMarkup = { id: 456 };
        const ecomMarkupTertiary: IEcomMarkupTertiary = { id: 16595 };
        ecomStoreMarkup.ecomMarkupTertiary = ecomMarkupTertiary;

        const ecomMarkupTertiaryCollection: IEcomMarkupTertiary[] = [{ id: 39800 }];
        spyOn(ecomMarkupTertiaryService, 'query').and.returnValue(of(new HttpResponse({ body: ecomMarkupTertiaryCollection })));
        const expectedCollection: IEcomMarkupTertiary[] = [ecomMarkupTertiary, ...ecomMarkupTertiaryCollection];
        spyOn(ecomMarkupTertiaryService, 'addEcomMarkupTertiaryToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStoreMarkup });
        comp.ngOnInit();

        expect(ecomMarkupTertiaryService.query).toHaveBeenCalled();
        expect(ecomMarkupTertiaryService.addEcomMarkupTertiaryToCollectionIfMissing).toHaveBeenCalledWith(
          ecomMarkupTertiaryCollection,
          ecomMarkupTertiary
        );
        expect(comp.ecomMarkupTertiariesCollection).toEqual(expectedCollection);
      });

      it('Should call ecomMarkupQuaternary query and add missing value', () => {
        const ecomStoreMarkup: IEcomStoreMarkup = { id: 456 };
        const ecomMarkupQuaternary: IEcomMarkupQuaternary = { id: 57829 };
        ecomStoreMarkup.ecomMarkupQuaternary = ecomMarkupQuaternary;

        const ecomMarkupQuaternaryCollection: IEcomMarkupQuaternary[] = [{ id: 29672 }];
        spyOn(ecomMarkupQuaternaryService, 'query').and.returnValue(of(new HttpResponse({ body: ecomMarkupQuaternaryCollection })));
        const expectedCollection: IEcomMarkupQuaternary[] = [ecomMarkupQuaternary, ...ecomMarkupQuaternaryCollection];
        spyOn(ecomMarkupQuaternaryService, 'addEcomMarkupQuaternaryToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomStoreMarkup });
        comp.ngOnInit();

        expect(ecomMarkupQuaternaryService.query).toHaveBeenCalled();
        expect(ecomMarkupQuaternaryService.addEcomMarkupQuaternaryToCollectionIfMissing).toHaveBeenCalledWith(
          ecomMarkupQuaternaryCollection,
          ecomMarkupQuaternary
        );
        expect(comp.ecomMarkupQuaternariesCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const ecomStoreMarkup: IEcomStoreMarkup = { id: 456 };
        const ecomMarkupPrimary: IEcomMarkupPrimary = { id: 13632 };
        ecomStoreMarkup.ecomMarkupPrimary = ecomMarkupPrimary;
        const ecomMarkupSecondary: IEcomMarkupSecondary = { id: 11689 };
        ecomStoreMarkup.ecomMarkupSecondary = ecomMarkupSecondary;
        const ecomMarkupTertiary: IEcomMarkupTertiary = { id: 54307 };
        ecomStoreMarkup.ecomMarkupTertiary = ecomMarkupTertiary;
        const ecomMarkupQuaternary: IEcomMarkupQuaternary = { id: 81653 };
        ecomStoreMarkup.ecomMarkupQuaternary = ecomMarkupQuaternary;

        activatedRoute.data = of({ ecomStoreMarkup });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomStoreMarkup));
        expect(comp.ecomMarkupPrimariesCollection).toContain(ecomMarkupPrimary);
        expect(comp.ecomMarkupSecondariesCollection).toContain(ecomMarkupSecondary);
        expect(comp.ecomMarkupTertiariesCollection).toContain(ecomMarkupTertiary);
        expect(comp.ecomMarkupQuaternariesCollection).toContain(ecomMarkupQuaternary);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreMarkup = { id: 123 };
        spyOn(ecomStoreMarkupService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreMarkup });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStoreMarkup }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomStoreMarkupService.update).toHaveBeenCalledWith(ecomStoreMarkup);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreMarkup = new EcomStoreMarkup();
        spyOn(ecomStoreMarkupService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreMarkup });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStoreMarkup }));
        saveSubject.complete();

        // THEN
        expect(ecomStoreMarkupService.create).toHaveBeenCalledWith(ecomStoreMarkup);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreMarkup = { id: 123 };
        spyOn(ecomStoreMarkupService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreMarkup });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomStoreMarkupService.update).toHaveBeenCalledWith(ecomStoreMarkup);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackEcomMarkupPrimaryById', () => {
        it('Should return tracked EcomMarkupPrimary primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomMarkupPrimaryById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackEcomMarkupSecondaryById', () => {
        it('Should return tracked EcomMarkupSecondary primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomMarkupSecondaryById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackEcomMarkupTertiaryById', () => {
        it('Should return tracked EcomMarkupTertiary primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomMarkupTertiaryById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });

      describe('trackEcomMarkupQuaternaryById', () => {
        it('Should return tracked EcomMarkupQuaternary primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomMarkupQuaternaryById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});
