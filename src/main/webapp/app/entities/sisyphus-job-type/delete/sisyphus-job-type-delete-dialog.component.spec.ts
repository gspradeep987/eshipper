jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { SisyphusJobTypeService } from '../service/sisyphus-job-type.service';

import { SisyphusJobTypeDeleteDialogComponent } from './sisyphus-job-type-delete-dialog.component';

describe('Component Tests', () => {
  describe('SisyphusJobType Management Delete Component', () => {
    let comp: SisyphusJobTypeDeleteDialogComponent;
    let fixture: ComponentFixture<SisyphusJobTypeDeleteDialogComponent>;
    let service: SisyphusJobTypeService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [SisyphusJobTypeDeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(SisyphusJobTypeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SisyphusJobTypeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(SisyphusJobTypeService);
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
