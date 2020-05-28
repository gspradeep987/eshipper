import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesCommissionDetailsUpdateComponent } from 'app/entities/wo-sales-commission-details/wo-sales-commission-details-update.component';
import { WoSalesCommissionDetailsService } from 'app/entities/wo-sales-commission-details/wo-sales-commission-details.service';
import { WoSalesCommissionDetails } from 'app/shared/model/wo-sales-commission-details.model';

describe('Component Tests', () => {
  describe('WoSalesCommissionDetails Management Update Component', () => {
    let comp: WoSalesCommissionDetailsUpdateComponent;
    let fixture: ComponentFixture<WoSalesCommissionDetailsUpdateComponent>;
    let service: WoSalesCommissionDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesCommissionDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(WoSalesCommissionDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoSalesCommissionDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoSalesCommissionDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoSalesCommissionDetails(123);
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
        const entity = new WoSalesCommissionDetails();
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
