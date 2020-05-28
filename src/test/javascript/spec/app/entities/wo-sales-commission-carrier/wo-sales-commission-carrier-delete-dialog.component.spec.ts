import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EshipperTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { WoSalesCommissionCarrierDeleteDialogComponent } from 'app/entities/wo-sales-commission-carrier/wo-sales-commission-carrier-delete-dialog.component';
import { WoSalesCommissionCarrierService } from 'app/entities/wo-sales-commission-carrier/wo-sales-commission-carrier.service';

describe('Component Tests', () => {
  describe('WoSalesCommissionCarrier Management Delete Component', () => {
    let comp: WoSalesCommissionCarrierDeleteDialogComponent;
    let fixture: ComponentFixture<WoSalesCommissionCarrierDeleteDialogComponent>;
    let service: WoSalesCommissionCarrierService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesCommissionCarrierDeleteDialogComponent],
      })
        .overrideTemplate(WoSalesCommissionCarrierDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoSalesCommissionCarrierDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoSalesCommissionCarrierService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
