import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreShipmentSettingsUpdateComponent } from 'app/entities/ecom-store-shipment-settings/ecom-store-shipment-settings-update.component';
import { EcomStoreShipmentSettingsService } from 'app/entities/ecom-store-shipment-settings/ecom-store-shipment-settings.service';
import { EcomStoreShipmentSettings } from 'app/shared/model/ecom-store-shipment-settings.model';

describe('Component Tests', () => {
  describe('EcomStoreShipmentSettings Management Update Component', () => {
    let comp: EcomStoreShipmentSettingsUpdateComponent;
    let fixture: ComponentFixture<EcomStoreShipmentSettingsUpdateComponent>;
    let service: EcomStoreShipmentSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreShipmentSettingsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomStoreShipmentSettingsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreShipmentSettingsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreShipmentSettingsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomStoreShipmentSettings(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomStoreShipmentSettings();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
