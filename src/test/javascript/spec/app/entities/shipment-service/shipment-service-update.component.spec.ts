import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ShipmentServiceUpdateComponent } from 'app/entities/shipment-service/shipment-service-update.component';
import { ShipmentServiceService } from 'app/entities/shipment-service/shipment-service.service';
import { ShipmentService } from 'app/shared/model/shipment-service.model';

describe('Component Tests', () => {
  describe('ShipmentService Management Update Component', () => {
    let comp: ShipmentServiceUpdateComponent;
    let fixture: ComponentFixture<ShipmentServiceUpdateComponent>;
    let service: ShipmentServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ShipmentServiceUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ShipmentServiceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShipmentServiceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShipmentServiceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ShipmentService(123);
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
        const entity = new ShipmentService();
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
