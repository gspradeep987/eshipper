jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomStoreSyncService } from '../service/ecom-store-sync.service';
import { IEcomStoreSync, EcomStoreSync } from '../ecom-store-sync.model';

import { EcomStoreSyncUpdateComponent } from './ecom-store-sync-update.component';

describe('Component Tests', () => {
  describe('EcomStoreSync Management Update Component', () => {
    let comp: EcomStoreSyncUpdateComponent;
    let fixture: ComponentFixture<EcomStoreSyncUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomStoreSyncService: EcomStoreSyncService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStoreSyncUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomStoreSyncUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreSyncUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomStoreSyncService = TestBed.inject(EcomStoreSyncService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const ecomStoreSync: IEcomStoreSync = { id: 456 };

        activatedRoute.data = of({ ecomStoreSync });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomStoreSync));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreSync = { id: 123 };
        spyOn(ecomStoreSyncService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreSync });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStoreSync }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomStoreSyncService.update).toHaveBeenCalledWith(ecomStoreSync);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreSync = new EcomStoreSync();
        spyOn(ecomStoreSyncService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreSync });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStoreSync }));
        saveSubject.complete();

        // THEN
        expect(ecomStoreSyncService.create).toHaveBeenCalledWith(ecomStoreSync);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreSync = { id: 123 };
        spyOn(ecomStoreSyncService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreSync });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomStoreSyncService.update).toHaveBeenCalledWith(ecomStoreSync);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
