jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { EcomStoreColorThemeService } from '../service/ecom-store-color-theme.service';

import { EcomStoreColorThemeDeleteDialogComponent } from './ecom-store-color-theme-delete-dialog.component';

describe('Component Tests', () => {
  describe('EcomStoreColorTheme Management Delete Component', () => {
    let comp: EcomStoreColorThemeDeleteDialogComponent;
    let fixture: ComponentFixture<EcomStoreColorThemeDeleteDialogComponent>;
    let service: EcomStoreColorThemeService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStoreColorThemeDeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(EcomStoreColorThemeDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStoreColorThemeDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomStoreColorThemeService);
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
