jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomMarkupPrimaryService } from '../service/ecom-markup-primary.service';
import { IEcomMarkupPrimary, EcomMarkupPrimary } from '../ecom-markup-primary.model';

import { EcomMarkupPrimaryUpdateComponent } from './ecom-markup-primary-update.component';

describe('Component Tests', () => {
  describe('EcomMarkupPrimary Management Update Component', () => {
    let comp: EcomMarkupPrimaryUpdateComponent;
    let fixture: ComponentFixture<EcomMarkupPrimaryUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomMarkupPrimaryService: EcomMarkupPrimaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomMarkupPrimaryUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomMarkupPrimaryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupPrimaryUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomMarkupPrimaryService = TestBed.inject(EcomMarkupPrimaryService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const ecomMarkupPrimary: IEcomMarkupPrimary = { id: 456 };

        activatedRoute.data = of({ ecomMarkupPrimary });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomMarkupPrimary));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMarkupPrimary = { id: 123 };
        spyOn(ecomMarkupPrimaryService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMarkupPrimary });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomMarkupPrimary }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomMarkupPrimaryService.update).toHaveBeenCalledWith(ecomMarkupPrimary);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMarkupPrimary = new EcomMarkupPrimary();
        spyOn(ecomMarkupPrimaryService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMarkupPrimary });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomMarkupPrimary }));
        saveSubject.complete();

        // THEN
        expect(ecomMarkupPrimaryService.create).toHaveBeenCalledWith(ecomMarkupPrimary);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomMarkupPrimary = { id: 123 };
        spyOn(ecomMarkupPrimaryService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomMarkupPrimary });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomMarkupPrimaryService.update).toHaveBeenCalledWith(ecomMarkupPrimary);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
