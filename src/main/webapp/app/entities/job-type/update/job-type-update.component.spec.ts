jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { JobTypeService } from '../service/job-type.service';
import { IJobType, JobType } from '../job-type.model';
import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';
import { SisyphusJobService } from 'app/entities/sisyphus-job/service/sisyphus-job.service';

import { JobTypeUpdateComponent } from './job-type-update.component';

describe('Component Tests', () => {
  describe('JobType Management Update Component', () => {
    let comp: JobTypeUpdateComponent;
    let fixture: ComponentFixture<JobTypeUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let jobTypeService: JobTypeService;
    let sisyphusJobService: SisyphusJobService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [JobTypeUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(JobTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(JobTypeUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      jobTypeService = TestBed.inject(JobTypeService);
      sisyphusJobService = TestBed.inject(SisyphusJobService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call SisyphusJob query and add missing value', () => {
        const jobType: IJobType = { id: 456 };
        const jobType: ISisyphusJob = { id: 10333 };
        jobType.jobType = jobType;

        const sisyphusJobCollection: ISisyphusJob[] = [{ id: 47516 }];
        spyOn(sisyphusJobService, 'query').and.returnValue(of(new HttpResponse({ body: sisyphusJobCollection })));
        const additionalSisyphusJobs = [jobType];
        const expectedCollection: ISisyphusJob[] = [...additionalSisyphusJobs, ...sisyphusJobCollection];
        spyOn(sisyphusJobService, 'addSisyphusJobToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ jobType });
        comp.ngOnInit();

        expect(sisyphusJobService.query).toHaveBeenCalled();
        expect(sisyphusJobService.addSisyphusJobToCollectionIfMissing).toHaveBeenCalledWith(
          sisyphusJobCollection,
          ...additionalSisyphusJobs
        );
        expect(comp.sisyphusJobsSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const jobType: IJobType = { id: 456 };
        const jobType: ISisyphusJob = { id: 97604 };
        jobType.jobType = jobType;

        activatedRoute.data = of({ jobType });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(jobType));
        expect(comp.sisyphusJobsSharedCollection).toContain(jobType);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const jobType = { id: 123 };
        spyOn(jobTypeService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ jobType });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: jobType }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(jobTypeService.update).toHaveBeenCalledWith(jobType);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const jobType = new JobType();
        spyOn(jobTypeService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ jobType });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: jobType }));
        saveSubject.complete();

        // THEN
        expect(jobTypeService.create).toHaveBeenCalledWith(jobType);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const jobType = { id: 123 };
        spyOn(jobTypeService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ jobType });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(jobTypeService.update).toHaveBeenCalledWith(jobType);
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
