jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomStoreShipmentSettingsService } from '../service/ecom-store-shipment-settings.service';
import { IEcomStoreShipmentSettings, EcomStoreShipmentSettings } from '../ecom-store-shipment-settings.model';

import { EcomStoreShipmentSettingsUpdateComponent } from './ecom-store-shipment-settings-update.component';

describe('Component Tests', () => {
  describe('EcomStoreShipmentSettings Management Update Component', () => {
    let comp: EcomStoreShipmentSettingsUpdateComponent;
    let fixture: ComponentFixture<EcomStoreShipmentSettingsUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomStoreShipmentSettingsService: EcomStoreShipmentSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStoreShipmentSettingsUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomStoreShipmentSettingsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreShipmentSettingsUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomStoreShipmentSettingsService = TestBed.inject(EcomStoreShipmentSettingsService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const ecomStoreShipmentSettings: IEcomStoreShipmentSettings = { id: 456 };

        activatedRoute.data = of({ ecomStoreShipmentSettings });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomStoreShipmentSettings));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreShipmentSettings = { id: 123 };
        spyOn(ecomStoreShipmentSettingsService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreShipmentSettings });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStoreShipmentSettings }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomStoreShipmentSettingsService.update).toHaveBeenCalledWith(ecomStoreShipmentSettings);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreShipmentSettings = new EcomStoreShipmentSettings();
        spyOn(ecomStoreShipmentSettingsService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreShipmentSettings });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStoreShipmentSettings }));
        saveSubject.complete();

        // THEN
        expect(ecomStoreShipmentSettingsService.create).toHaveBeenCalledWith(ecomStoreShipmentSettings);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreShipmentSettings = { id: 123 };
        spyOn(ecomStoreShipmentSettingsService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreShipmentSettings });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomStoreShipmentSettingsService.update).toHaveBeenCalledWith(ecomStoreShipmentSettings);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
