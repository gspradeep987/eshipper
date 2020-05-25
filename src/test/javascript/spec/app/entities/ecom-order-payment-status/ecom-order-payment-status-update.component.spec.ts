import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderPaymentStatusUpdateComponent } from 'app/entities/ecom-order-payment-status/ecom-order-payment-status-update.component';
import { EcomOrderPaymentStatusService } from 'app/entities/ecom-order-payment-status/ecom-order-payment-status.service';
import { EcomOrderPaymentStatus } from 'app/shared/model/ecom-order-payment-status.model';

describe('Component Tests', () => {
  describe('EcomOrderPaymentStatus Management Update Component', () => {
    let comp: EcomOrderPaymentStatusUpdateComponent;
    let fixture: ComponentFixture<EcomOrderPaymentStatusUpdateComponent>;
    let service: EcomOrderPaymentStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderPaymentStatusUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EcomOrderPaymentStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomOrderPaymentStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomOrderPaymentStatusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomOrderPaymentStatus(123);
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
        const entity = new EcomOrderPaymentStatus();
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
