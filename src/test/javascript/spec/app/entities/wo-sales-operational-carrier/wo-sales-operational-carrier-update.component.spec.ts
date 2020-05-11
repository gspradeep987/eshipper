import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesOperationalCarrierUpdateComponent } from 'app/entities/wo-sales-operational-carrier/wo-sales-operational-carrier-update.component';
import { WoSalesOperationalCarrierService } from 'app/entities/wo-sales-operational-carrier/wo-sales-operational-carrier.service';
import { WoSalesOperationalCarrier } from 'app/shared/model/wo-sales-operational-carrier.model';

describe('Component Tests', () => {
  describe('WoSalesOperationalCarrier Management Update Component', () => {
    let comp: WoSalesOperationalCarrierUpdateComponent;
    let fixture: ComponentFixture<WoSalesOperationalCarrierUpdateComponent>;
    let service: WoSalesOperationalCarrierService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesOperationalCarrierUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WoSalesOperationalCarrierUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoSalesOperationalCarrierUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoSalesOperationalCarrierService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoSalesOperationalCarrier(123);
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
        const entity = new WoSalesOperationalCarrier();
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
