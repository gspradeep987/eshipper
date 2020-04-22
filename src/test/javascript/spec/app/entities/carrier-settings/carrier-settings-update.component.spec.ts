import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CarrierSettingsUpdateComponent } from 'app/entities/carrier-settings/carrier-settings-update.component';
import { CarrierSettingsService } from 'app/entities/carrier-settings/carrier-settings.service';
import { CarrierSettings } from 'app/shared/model/carrier-settings.model';

describe('Component Tests', () => {
  describe('CarrierSettings Management Update Component', () => {
    let comp: CarrierSettingsUpdateComponent;
    let fixture: ComponentFixture<CarrierSettingsUpdateComponent>;
    let service: CarrierSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CarrierSettingsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CarrierSettingsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CarrierSettingsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CarrierSettingsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CarrierSettings(123);
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
        const entity = new CarrierSettings();
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
