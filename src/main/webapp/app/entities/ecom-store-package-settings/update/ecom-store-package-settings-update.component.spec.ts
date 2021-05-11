jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomStorePackageSettingsService } from '../service/ecom-store-package-settings.service';
import { IEcomStorePackageSettings, EcomStorePackageSettings } from '../ecom-store-package-settings.model';

import { EcomStorePackageSettingsUpdateComponent } from './ecom-store-package-settings-update.component';

describe('Component Tests', () => {
  describe('EcomStorePackageSettings Management Update Component', () => {
    let comp: EcomStorePackageSettingsUpdateComponent;
    let fixture: ComponentFixture<EcomStorePackageSettingsUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomStorePackageSettingsService: EcomStorePackageSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStorePackageSettingsUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomStorePackageSettingsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStorePackageSettingsUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomStorePackageSettingsService = TestBed.inject(EcomStorePackageSettingsService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const ecomStorePackageSettings: IEcomStorePackageSettings = { id: 456 };

        activatedRoute.data = of({ ecomStorePackageSettings });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomStorePackageSettings));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStorePackageSettings = { id: 123 };
        spyOn(ecomStorePackageSettingsService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStorePackageSettings });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStorePackageSettings }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomStorePackageSettingsService.update).toHaveBeenCalledWith(ecomStorePackageSettings);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStorePackageSettings = new EcomStorePackageSettings();
        spyOn(ecomStorePackageSettingsService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStorePackageSettings });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStorePackageSettings }));
        saveSubject.complete();

        // THEN
        expect(ecomStorePackageSettingsService.create).toHaveBeenCalledWith(ecomStorePackageSettings);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStorePackageSettings = { id: 123 };
        spyOn(ecomStorePackageSettingsService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStorePackageSettings });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomStorePackageSettingsService.update).toHaveBeenCalledWith(ecomStorePackageSettings);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
