import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CarrierServiceUpdateComponent } from 'app/entities/carrier-service/carrier-service-update.component';
import { CarrierServiceService } from 'app/entities/carrier-service/carrier-service.service';
import { CarrierService } from 'app/shared/model/carrier-service.model';

describe('Component Tests', () => {
  describe('CarrierService Management Update Component', () => {
    let comp: CarrierServiceUpdateComponent;
    let fixture: ComponentFixture<CarrierServiceUpdateComponent>;
    let service: CarrierServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CarrierServiceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CarrierServiceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CarrierServiceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CarrierServiceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CarrierService(123);
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
        const entity = new CarrierService();
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
