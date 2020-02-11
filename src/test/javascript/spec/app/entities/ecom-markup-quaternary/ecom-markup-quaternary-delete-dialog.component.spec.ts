import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EshipperTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { EcomMarkupQuaternaryDeleteDialogComponent } from 'app/entities/ecom-markup-quaternary/ecom-markup-quaternary-delete-dialog.component';
import { EcomMarkupQuaternaryService } from 'app/entities/ecom-markup-quaternary/ecom-markup-quaternary.service';

describe('Component Tests', () => {
  describe('EcomMarkupQuaternary Management Delete Component', () => {
    let comp: EcomMarkupQuaternaryDeleteDialogComponent;
    let fixture: ComponentFixture<EcomMarkupQuaternaryDeleteDialogComponent>;
    let service: EcomMarkupQuaternaryService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMarkupQuaternaryDeleteDialogComponent]
      })
        .overrideTemplate(EcomMarkupQuaternaryDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomMarkupQuaternaryDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomMarkupQuaternaryService);
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
