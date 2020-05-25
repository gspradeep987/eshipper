import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderFullfillmentStatusUpdateComponent } from 'app/entities/ecom-order-fullfillment-status/ecom-order-fullfillment-status-update.component';
import { EcomOrderFullfillmentStatusService } from 'app/entities/ecom-order-fullfillment-status/ecom-order-fullfillment-status.service';
import { EcomOrderFullfillmentStatus } from 'app/shared/model/ecom-order-fullfillment-status.model';

describe('Component Tests', () => {
  describe('EcomOrderFullfillmentStatus Management Update Component', () => {
    let comp: EcomOrderFullfillmentStatusUpdateComponent;
    let fixture: ComponentFixture<EcomOrderFullfillmentStatusUpdateComponent>;
    let service: EcomOrderFullfillmentStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderFullfillmentStatusUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EcomOrderFullfillmentStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomOrderFullfillmentStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomOrderFullfillmentStatusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomOrderFullfillmentStatus(123);
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
        const entity = new EcomOrderFullfillmentStatus();
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
