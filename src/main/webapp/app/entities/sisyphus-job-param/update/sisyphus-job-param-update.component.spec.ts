jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { SisyphusJobParamService } from '../service/sisyphus-job-param.service';
import { ISisyphusJobParam, SisyphusJobParam } from '../sisyphus-job-param.model';
import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';
import { SisyphusJobService } from 'app/entities/sisyphus-job/service/sisyphus-job.service';

import { SisyphusJobParamUpdateComponent } from './sisyphus-job-param-update.component';

describe('Component Tests', () => {
  describe('SisyphusJobParam Management Update Component', () => {
    let comp: SisyphusJobParamUpdateComponent;
    let fixture: ComponentFixture<SisyphusJobParamUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let sisyphusJobParamService: SisyphusJobParamService;
    let sisyphusJobService: SisyphusJobService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [SisyphusJobParamUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(SisyphusJobParamUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SisyphusJobParamUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      sisyphusJobParamService = TestBed.inject(SisyphusJobParamService);
      sisyphusJobService = TestBed.inject(SisyphusJobService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call SisyphusJob query and add missing value', () => {
        const sisyphusJobParam: ISisyphusJobParam = { id: 456 };
        const sisyphusJob: ISisyphusJob = { id: 71293 };
        sisyphusJobParam.sisyphusJob = sisyphusJob;

        const sisyphusJobCollection: ISisyphusJob[] = [{ id: 78195 }];
        spyOn(sisyphusJobService, 'query').and.returnValue(of(new HttpResponse({ body: sisyphusJobCollection })));
        const additionalSisyphusJobs = [sisyphusJob];
        const expectedCollection: ISisyphusJob[] = [...additionalSisyphusJobs, ...sisyphusJobCollection];
        spyOn(sisyphusJobService, 'addSisyphusJobToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ sisyphusJobParam });
        comp.ngOnInit();

        expect(sisyphusJobService.query).toHaveBeenCalled();
        expect(sisyphusJobService.addSisyphusJobToCollectionIfMissing).toHaveBeenCalledWith(
          sisyphusJobCollection,
          ...additionalSisyphusJobs
        );
        expect(comp.sisyphusJobsSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const sisyphusJobParam: ISisyphusJobParam = { id: 456 };
        const sisyphusJob: ISisyphusJob = { id: 32256 };
        sisyphusJobParam.sisyphusJob = sisyphusJob;

        activatedRoute.data = of({ sisyphusJobParam });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(sisyphusJobParam));
        expect(comp.sisyphusJobsSharedCollection).toContain(sisyphusJob);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusJobParam = { id: 123 };
        spyOn(sisyphusJobParamService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusJobParam });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: sisyphusJobParam }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(sisyphusJobParamService.update).toHaveBeenCalledWith(sisyphusJobParam);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusJobParam = new SisyphusJobParam();
        spyOn(sisyphusJobParamService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusJobParam });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: sisyphusJobParam }));
        saveSubject.complete();

        // THEN
        expect(sisyphusJobParamService.create).toHaveBeenCalledWith(sisyphusJobParam);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusJobParam = { id: 123 };
        spyOn(sisyphusJobParamService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusJobParam });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(sisyphusJobParamService.update).toHaveBeenCalledWith(sisyphusJobParam);
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
