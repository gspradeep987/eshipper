import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { EshipperTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { ElasticSearchWoSalesAgentDeleteDialogComponent } from 'app/entities/elastic-search-wo-sales-agent/elastic-search-wo-sales-agent-delete-dialog.component';
import { ElasticSearchWoSalesAgentService } from 'app/entities/elastic-search-wo-sales-agent/elastic-search-wo-sales-agent.service';

describe('Component Tests', () => {
  describe('ElasticSearchWoSalesAgent Management Delete Component', () => {
    let comp: ElasticSearchWoSalesAgentDeleteDialogComponent;
    let fixture: ComponentFixture<ElasticSearchWoSalesAgentDeleteDialogComponent>;
    let service: ElasticSearchWoSalesAgentService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticSearchWoSalesAgentDeleteDialogComponent],
      })
        .overrideTemplate(ElasticSearchWoSalesAgentDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ElasticSearchWoSalesAgentDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ElasticSearchWoSalesAgentService);
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
