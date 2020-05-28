import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesCommissionTransferUpdateComponent } from 'app/entities/wo-sales-commission-transfer/wo-sales-commission-transfer-update.component';
import { WoSalesCommissionTransferService } from 'app/entities/wo-sales-commission-transfer/wo-sales-commission-transfer.service';
import { WoSalesCommissionTransfer } from 'app/shared/model/wo-sales-commission-transfer.model';

describe('Component Tests', () => {
  describe('WoSalesCommissionTransfer Management Update Component', () => {
    let comp: WoSalesCommissionTransferUpdateComponent;
    let fixture: ComponentFixture<WoSalesCommissionTransferUpdateComponent>;
    let service: WoSalesCommissionTransferService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesCommissionTransferUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(WoSalesCommissionTransferUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoSalesCommissionTransferUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoSalesCommissionTransferService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoSalesCommissionTransfer(123);
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
        const entity = new WoSalesCommissionTransfer();
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
