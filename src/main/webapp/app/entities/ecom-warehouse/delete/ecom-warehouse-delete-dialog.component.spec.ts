jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { EcomWarehouseService } from '../service/ecom-warehouse.service';

import { EcomWarehouseDeleteDialogComponent } from './ecom-warehouse-delete-dialog.component';

describe('Component Tests', () => {
  describe('EcomWarehouse Management Delete Component', () => {
    let comp: EcomWarehouseDeleteDialogComponent;
    let fixture: ComponentFixture<EcomWarehouseDeleteDialogComponent>;
    let service: EcomWarehouseService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomWarehouseDeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(EcomWarehouseDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomWarehouseDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomWarehouseService);
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
