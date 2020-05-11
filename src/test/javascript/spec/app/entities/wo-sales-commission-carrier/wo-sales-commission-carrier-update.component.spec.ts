import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesCommissionCarrierUpdateComponent } from 'app/entities/wo-sales-commission-carrier/wo-sales-commission-carrier-update.component';
import { WoSalesCommissionCarrierService } from 'app/entities/wo-sales-commission-carrier/wo-sales-commission-carrier.service';
import { WoSalesCommissionCarrier } from 'app/shared/model/wo-sales-commission-carrier.model';

describe('Component Tests', () => {
  describe('WoSalesCommissionCarrier Management Update Component', () => {
    let comp: WoSalesCommissionCarrierUpdateComponent;
    let fixture: ComponentFixture<WoSalesCommissionCarrierUpdateComponent>;
    let service: WoSalesCommissionCarrierService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesCommissionCarrierUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WoSalesCommissionCarrierUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoSalesCommissionCarrierUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoSalesCommissionCarrierService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoSalesCommissionCarrier(123);
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
        const entity = new WoSalesCommissionCarrier();
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
