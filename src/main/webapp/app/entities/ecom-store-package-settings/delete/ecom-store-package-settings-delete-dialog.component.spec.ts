jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { EcomStorePackageSettingsService } from '../service/ecom-store-package-settings.service';

import { EcomStorePackageSettingsDeleteDialogComponent } from './ecom-store-package-settings-delete-dialog.component';

describe('Component Tests', () => {
  describe('EcomStorePackageSettings Management Delete Component', () => {
    let comp: EcomStorePackageSettingsDeleteDialogComponent;
    let fixture: ComponentFixture<EcomStorePackageSettingsDeleteDialogComponent>;
    let service: EcomStorePackageSettingsService;
    let mockActiveModal: NgbActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStorePackageSettingsDeleteDialogComponent],
        providers: [NgbActiveModal],
      })
        .overrideTemplate(EcomStorePackageSettingsDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStorePackageSettingsDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomStorePackageSettingsService);
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
