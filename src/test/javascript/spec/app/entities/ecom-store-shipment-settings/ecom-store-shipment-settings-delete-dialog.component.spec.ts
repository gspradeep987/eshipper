import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EshipperTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { EcomStoreShipmentSettingsDeleteDialogComponent } from 'app/entities/ecom-store-shipment-settings/ecom-store-shipment-settings-delete-dialog.component';
import { EcomStoreShipmentSettingsService } from 'app/entities/ecom-store-shipment-settings/ecom-store-shipment-settings.service';

describe('Component Tests', () => {
  describe('EcomStoreShipmentSettings Management Delete Component', () => {
    let comp: EcomStoreShipmentSettingsDeleteDialogComponent;
    let fixture: ComponentFixture<EcomStoreShipmentSettingsDeleteDialogComponent>;
    let service: EcomStoreShipmentSettingsService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreShipmentSettingsDeleteDialogComponent]
      })
        .overrideTemplate(EcomStoreShipmentSettingsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStoreShipmentSettingsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreShipmentSettingsService);
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
