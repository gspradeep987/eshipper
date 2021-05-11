jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomStoreColorThemeService } from '../service/ecom-store-color-theme.service';
import { IEcomStoreColorTheme, EcomStoreColorTheme } from '../ecom-store-color-theme.model';

import { EcomStoreColorThemeUpdateComponent } from './ecom-store-color-theme-update.component';

describe('Component Tests', () => {
  describe('EcomStoreColorTheme Management Update Component', () => {
    let comp: EcomStoreColorThemeUpdateComponent;
    let fixture: ComponentFixture<EcomStoreColorThemeUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomStoreColorThemeService: EcomStoreColorThemeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStoreColorThemeUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomStoreColorThemeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreColorThemeUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomStoreColorThemeService = TestBed.inject(EcomStoreColorThemeService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const ecomStoreColorTheme: IEcomStoreColorTheme = { id: 456 };

        activatedRoute.data = of({ ecomStoreColorTheme });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomStoreColorTheme));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreColorTheme = { id: 123 };
        spyOn(ecomStoreColorThemeService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreColorTheme });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStoreColorTheme }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomStoreColorThemeService.update).toHaveBeenCalledWith(ecomStoreColorTheme);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreColorTheme = new EcomStoreColorTheme();
        spyOn(ecomStoreColorThemeService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreColorTheme });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomStoreColorTheme }));
        saveSubject.complete();

        // THEN
        expect(ecomStoreColorThemeService.create).toHaveBeenCalledWith(ecomStoreColorTheme);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomStoreColorTheme = { id: 123 };
        spyOn(ecomStoreColorThemeService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomStoreColorTheme });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomStoreColorThemeService.update).toHaveBeenCalledWith(ecomStoreColorTheme);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
