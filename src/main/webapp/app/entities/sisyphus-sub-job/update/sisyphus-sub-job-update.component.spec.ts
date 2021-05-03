jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { SisyphusSubJobService } from '../service/sisyphus-sub-job.service';
import { ISisyphusSubJob, SisyphusSubJob } from '../sisyphus-sub-job.model';
import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';
import { SisyphusJobService } from 'app/entities/sisyphus-job/service/sisyphus-job.service';

import { SisyphusSubJobUpdateComponent } from './sisyphus-sub-job-update.component';

describe('Component Tests', () => {
  describe('SisyphusSubJob Management Update Component', () => {
    let comp: SisyphusSubJobUpdateComponent;
    let fixture: ComponentFixture<SisyphusSubJobUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let sisyphusSubJobService: SisyphusSubJobService;
    let sisyphusJobService: SisyphusJobService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [SisyphusSubJobUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(SisyphusSubJobUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SisyphusSubJobUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      sisyphusSubJobService = TestBed.inject(SisyphusSubJobService);
      sisyphusJobService = TestBed.inject(SisyphusJobService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call SisyphusJob query and add missing value', () => {
        const sisyphusSubJob: ISisyphusSubJob = { id: 456 };
        const sisyphusJob: ISisyphusJob = { id: 5525 };
        sisyphusSubJob.sisyphusJob = sisyphusJob;

        const sisyphusJobCollection: ISisyphusJob[] = [{ id: 67852 }];
        spyOn(sisyphusJobService, 'query').and.returnValue(of(new HttpResponse({ body: sisyphusJobCollection })));
        const additionalSisyphusJobs = [sisyphusJob];
        const expectedCollection: ISisyphusJob[] = [...additionalSisyphusJobs, ...sisyphusJobCollection];
        spyOn(sisyphusJobService, 'addSisyphusJobToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ sisyphusSubJob });
        comp.ngOnInit();

        expect(sisyphusJobService.query).toHaveBeenCalled();
        expect(sisyphusJobService.addSisyphusJobToCollectionIfMissing).toHaveBeenCalledWith(
          sisyphusJobCollection,
          ...additionalSisyphusJobs
        );
        expect(comp.sisyphusJobsSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const sisyphusSubJob: ISisyphusSubJob = { id: 456 };
        const sisyphusJob: ISisyphusJob = { id: 71293 };
        sisyphusSubJob.sisyphusJob = sisyphusJob;

        activatedRoute.data = of({ sisyphusSubJob });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(sisyphusSubJob));
        expect(comp.sisyphusJobsSharedCollection).toContain(sisyphusJob);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusSubJob = { id: 123 };
        spyOn(sisyphusSubJobService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusSubJob });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: sisyphusSubJob }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(sisyphusSubJobService.update).toHaveBeenCalledWith(sisyphusSubJob);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusSubJob = new SisyphusSubJob();
        spyOn(sisyphusSubJobService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusSubJob });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: sisyphusSubJob }));
        saveSubject.complete();

        // THEN
        expect(sisyphusSubJobService.create).toHaveBeenCalledWith(sisyphusSubJob);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusSubJob = { id: 123 };
        spyOn(sisyphusSubJobService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusSubJob });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(sisyphusSubJobService.update).toHaveBeenCalledWith(sisyphusSubJob);
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
