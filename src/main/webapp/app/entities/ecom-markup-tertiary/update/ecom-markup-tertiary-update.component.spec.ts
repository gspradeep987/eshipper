jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomMarkupTertiaryService } from '../service/ecom-markup-tertiary.service';
import { IEcomMarkupTertiary, EcomMarkupTertiary } from '../ecom-markup-tertiary.model';

import { EcomMarkupTertiaryUpdateComponent } from './ecom-markup-tertiary-update.component';

describe('Component Tests', () => {
  describe('EcomMarkupTertiary Management Update Component', () => {
    let comp: EcomMarkupTertiaryUpdateComponent;
    let fixture: ComponentFixture<EcomMarkupTertiaryUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomMarkupTertiaryService: EcomMarkupTertiaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomMarkupTertiaryUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomMarkupTertiaryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupTertiaryUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomMarkupTertiaryService = TestBed.inject(EcomMarkupTertiaryService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const ecomMarkupTertiary: IEcomMarkupTertiary = { id: 456 };

        activatedRoute.data = of({ ecomMarkupTertiary });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomMarkupTertiary));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMarkupTertiary = { id: 123 };
        spyOn(ecomMarkupTertiaryService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMarkupTertiary });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomMarkupTertiary }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomMarkupTertiaryService.update).toHaveBeenCalledWith(ecomMarkupTertiary);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMarkupTertiary = new EcomMarkupTertiary();
        spyOn(ecomMarkupTertiaryService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMarkupTertiary });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomMarkupTertiary }));
        saveSubject.complete();

        // THEN
        expect(ecomMarkupTertiaryService.create).toHaveBeenCalledWith(ecomMarkupTertiary);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMarkupTertiary = { id: 123 };
        spyOn(ecomMarkupTertiaryService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMarkupTertiary });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomMarkupTertiaryService.update).toHaveBeenCalledWith(ecomMarkupTertiary);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
