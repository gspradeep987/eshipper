import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderUpdateComponent } from 'app/entities/ecom-order/ecom-order-update.component';
import { EcomOrderService } from 'app/entities/ecom-order/ecom-order.service';
import { EcomOrder } from 'app/shared/model/ecom-order.model';

describe('Component Tests', () => {
  describe('EcomOrder Management Update Component', () => {
    let comp: EcomOrderUpdateComponent;
    let fixture: ComponentFixture<EcomOrderUpdateComponent>;
    let service: EcomOrderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomOrderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomOrderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomOrderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomOrder(123);
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
        const entity = new EcomOrder();
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
