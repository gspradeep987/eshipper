jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { SisyphusJobTypeService } from '../service/sisyphus-job-type.service';
import { ISisyphusJobType, SisyphusJobType } from '../sisyphus-job-type.model';
import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';
import { SisyphusJobService } from 'app/entities/sisyphus-job/service/sisyphus-job.service';

import { SisyphusJobTypeUpdateComponent } from './sisyphus-job-type-update.component';

describe('Component Tests', () => {
  describe('SisyphusJobType Management Update Component', () => {
    let comp: SisyphusJobTypeUpdateComponent;
    let fixture: ComponentFixture<SisyphusJobTypeUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let sisyphusJobTypeService: SisyphusJobTypeService;
    let sisyphusJobService: SisyphusJobService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [SisyphusJobTypeUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(SisyphusJobTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SisyphusJobTypeUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      sisyphusJobTypeService = TestBed.inject(SisyphusJobTypeService);
      sisyphusJobService = TestBed.inject(SisyphusJobService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call SisyphusJob query and add missing value', () => {
        const sisyphusJobType: ISisyphusJobType = { id: 456 };
        const sisyphusJobType: ISisyphusJob = { id: 97604 };
        sisyphusJobType.sisyphusJobType = sisyphusJobType;

        const sisyphusJobCollection: ISisyphusJob[] = [{ id: 67598 }];
        spyOn(sisyphusJobService, 'query').and.returnValue(of(new HttpResponse({ body: sisyphusJobCollection })));
        const additionalSisyphusJobs = [sisyphusJobType];
        const expectedCollection: ISisyphusJob[] = [...additionalSisyphusJobs, ...sisyphusJobCollection];
        spyOn(sisyphusJobService, 'addSisyphusJobToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ sisyphusJobType });
        comp.ngOnInit();

        expect(sisyphusJobService.query).toHaveBeenCalled();
        expect(sisyphusJobService.addSisyphusJobToCollectionIfMissing).toHaveBeenCalledWith(
          sisyphusJobCollection,
          ...additionalSisyphusJobs
        );
        expect(comp.sisyphusJobsSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const sisyphusJobType: ISisyphusJobType = { id: 456 };
        const sisyphusJobType: ISisyphusJob = { id: 326 };
        sisyphusJobType.sisyphusJobType = sisyphusJobType;

        activatedRoute.data = of({ sisyphusJobType });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(sisyphusJobType));
        expect(comp.sisyphusJobsSharedCollection).toContain(sisyphusJobType);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusJobType = { id: 123 };
        spyOn(sisyphusJobTypeService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusJobType });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: sisyphusJobType }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(sisyphusJobTypeService.update).toHaveBeenCalledWith(sisyphusJobType);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusJobType = new SisyphusJobType();
        spyOn(sisyphusJobTypeService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusJobType });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: sisyphusJobType }));
        saveSubject.complete();

        // THEN
        expect(sisyphusJobTypeService.create).toHaveBeenCalledWith(sisyphusJobType);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusJobType = { id: 123 };
        spyOn(sisyphusJobTypeService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusJobType });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(sisyphusJobTypeService.update).toHaveBeenCalledWith(sisyphusJobType);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackSisyphusJobById', () => {
        it('Should return tracked SisyphusJob primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackSisyphusJobById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});
