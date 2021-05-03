jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomMailTemplateService } from '../service/ecom-mail-template.service';
import { IEcomMailTemplate, EcomMailTemplate } from '../ecom-mail-template.model';
import { IEcomStore } from 'app/entities/ecom-store/ecom-store.model';
import { EcomStoreService } from 'app/entities/ecom-store/service/ecom-store.service';

import { EcomMailTemplateUpdateComponent } from './ecom-mail-template-update.component';

describe('Component Tests', () => {
  describe('EcomMailTemplate Management Update Component', () => {
    let comp: EcomMailTemplateUpdateComponent;
    let fixture: ComponentFixture<EcomMailTemplateUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomMailTemplateService: EcomMailTemplateService;
    let ecomStoreService: EcomStoreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomMailTemplateUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomMailTemplateUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMailTemplateUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomMailTemplateService = TestBed.inject(EcomMailTemplateService);
      ecomStoreService = TestBed.inject(EcomStoreService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call EcomStore query and add missing value', () => {
        const ecomMailTemplate: IEcomMailTemplate = { id: 456 };
        const ecomStore: IEcomStore = { id: 30408 };
        ecomMailTemplate.ecomStore = ecomStore;

        const ecomStoreCollection: IEcomStore[] = [{ id: 60975 }];
        spyOn(ecomStoreService, 'query').and.returnValue(of(new HttpResponse({ body: ecomStoreCollection })));
        const additionalEcomStores = [ecomStore];
        const expectedCollection: IEcomStore[] = [...additionalEcomStores, ...ecomStoreCollection];
        spyOn(ecomStoreService, 'addEcomStoreToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomMailTemplate });
        comp.ngOnInit();

        expect(ecomStoreService.query).toHaveBeenCalled();
        expect(ecomStoreService.addEcomStoreToCollectionIfMissing).toHaveBeenCalledWith(ecomStoreCollection, ...additionalEcomStores);
        expect(comp.ecomStoresSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const ecomMailTemplate: IEcomMailTemplate = { id: 456 };
        const ecomStore: IEcomStore = { id: 12637 };
        ecomMailTemplate.ecomStore = ecomStore;

        activatedRoute.data = of({ ecomMailTemplate });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomMailTemplate));
        expect(comp.ecomStoresSharedCollection).toContain(ecomStore);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMailTemplate = { id: 123 };
        spyOn(ecomMailTemplateService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMailTemplate });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomMailTemplate }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomMailTemplateService.update).toHaveBeenCalledWith(ecomMailTemplate);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMailTemplate = new EcomMailTemplate();
        spyOn(ecomMailTemplateService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMailTemplate });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomMailTemplate }));
        saveSubject.complete();

        // THEN
        expect(ecomMailTemplateService.create).toHaveBeenCalledWith(ecomMailTemplate);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMailTemplate = { id: 123 };
        spyOn(ecomMailTemplateService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMailTemplate });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomMailTemplateService.update).toHaveBeenCalledWith(ecomMailTemplate);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
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
