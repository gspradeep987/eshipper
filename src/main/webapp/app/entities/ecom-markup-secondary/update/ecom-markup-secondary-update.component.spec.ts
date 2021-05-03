jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomMarkupSecondaryService } from '../service/ecom-markup-secondary.service';
import { IEcomMarkupSecondary, EcomMarkupSecondary } from '../ecom-markup-secondary.model';

import { EcomMarkupSecondaryUpdateComponent } from './ecom-markup-secondary-update.component';

describe('Component Tests', () => {
  describe('EcomMarkupSecondary Management Update Component', () => {
    let comp: EcomMarkupSecondaryUpdateComponent;
    let fixture: ComponentFixture<EcomMarkupSecondaryUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomMarkupSecondaryService: EcomMarkupSecondaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomMarkupSecondaryUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomMarkupSecondaryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupSecondaryUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomMarkupSecondaryService = TestBed.inject(EcomMarkupSecondaryService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const ecomMarkupSecondary: IEcomMarkupSecondary = { id: 456 };

        activatedRoute.data = of({ ecomMarkupSecondary });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomMarkupSecondary));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMarkupSecondary = { id: 123 };
        spyOn(ecomMarkupSecondaryService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMarkupSecondary });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomMarkupSecondary }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomMarkupSecondaryService.update).toHaveBeenCalledWith(ecomMarkupSecondary);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMarkupSecondary = new EcomMarkupSecondary();
        spyOn(ecomMarkupSecondaryService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMarkupSecondary });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomMarkupSecondary }));
        saveSubject.complete();

        // THEN
        expect(ecomMarkupSecondaryService.create).toHaveBeenCalledWith(ecomMarkupSecondary);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMarkupSecondary = { id: 123 };
        spyOn(ecomMarkupSecondaryService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMarkupSecondary });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomMarkupSecondaryService.update).toHaveBeenCalledWith(ecomMarkupSecondary);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
