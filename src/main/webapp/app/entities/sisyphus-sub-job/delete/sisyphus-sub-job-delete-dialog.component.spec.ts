jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { SisyphusSubJobService } from '../service/sisyphus-sub-job.service';

import { SisyphusSubJobDeleteDialogComponent } from './sisyphus-sub-job-delete-dialog.component';

describe('Component Tests', () => {
  describe('SisyphusSubJob Management Delete Component', () => {
    let comp: SisyphusSubJobDeleteDialogComponent;
    let fixture: ComponentFixture<SisyphusSubJobDeleteDialogComponent>;
    let service: SisyphusSubJobService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [SisyphusSubJobDeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(SisyphusSubJobDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SisyphusSubJobDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(SisyphusSubJobService);
      mockActiveModal = TestBed.inject(NgbActiveModal);
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
          expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.close).not.toHaveBeenCalled();
        expect(mockActiveModal.dismiss).toHaveBeenCalled();
      });
    });
  });
});
