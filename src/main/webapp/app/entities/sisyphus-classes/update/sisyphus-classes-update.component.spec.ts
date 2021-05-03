jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { SisyphusClassesService } from '../service/sisyphus-classes.service';
import { ISisyphusClasses, SisyphusClasses } from '../sisyphus-classes.model';
import { ISisyphusSubJob } from 'app/entities/sisyphus-sub-job/sisyphus-sub-job.model';
import { SisyphusSubJobService } from 'app/entities/sisyphus-sub-job/service/sisyphus-sub-job.service';

import { SisyphusClassesUpdateComponent } from './sisyphus-classes-update.component';

describe('Component Tests', () => {
  describe('SisyphusClasses Management Update Component', () => {
    let comp: SisyphusClassesUpdateComponent;
    let fixture: ComponentFixture<SisyphusClassesUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let sisyphusClassesService: SisyphusClassesService;
    let sisyphusSubJobService: SisyphusSubJobService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [SisyphusClassesUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(SisyphusClassesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SisyphusClassesUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      sisyphusClassesService = TestBed.inject(SisyphusClassesService);
      sisyphusSubJobService = TestBed.inject(SisyphusSubJobService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call SisyphusSubJob query and add missing value', () => {
        const sisyphusClasses: ISisyphusClasses = { id: 456 };
        const classes: ISisyphusSubJob = { id: 96526 };
        sisyphusClasses.classes = classes;

        const sisyphusSubJobCollection: ISisyphusSubJob[] = [{ id: 68576 }];
        spyOn(sisyphusSubJobService, 'query').and.returnValue(of(new HttpResponse({ body: sisyphusSubJobCollection })));
        const additionalSisyphusSubJobs = [classes];
        const expectedCollection: ISisyphusSubJob[] = [...additionalSisyphusSubJobs, ...sisyphusSubJobCollection];
        spyOn(sisyphusSubJobService, 'addSisyphusSubJobToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ sisyphusClasses });
        comp.ngOnInit();

        expect(sisyphusSubJobService.query).toHaveBeenCalled();
        expect(sisyphusSubJobService.addSisyphusSubJobToCollectionIfMissing).toHaveBeenCalledWith(
          sisyphusSubJobCollection,
          ...additionalSisyphusSubJobs
        );
        expect(comp.sisyphusSubJobsSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const sisyphusClasses: ISisyphusClasses = { id: 456 };
        const classes: ISisyphusSubJob = { id: 55875 };
        sisyphusClasses.classes = classes;

        activatedRoute.data = of({ sisyphusClasses });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(sisyphusClasses));
        expect(comp.sisyphusSubJobsSharedCollection).toContain(classes);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusClasses = { id: 123 };
        spyOn(sisyphusClassesService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusClasses });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: sisyphusClasses }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(sisyphusClassesService.update).toHaveBeenCalledWith(sisyphusClasses);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusClasses = new SisyphusClasses();
        spyOn(sisyphusClassesService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusClasses });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: sisyphusClasses }));
        saveSubject.complete();

        // THEN
        expect(sisyphusClassesService.create).toHaveBeenCalledWith(sisyphusClasses);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusClasses = { id: 123 };
        spyOn(sisyphusClassesService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusClasses });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(sisyphusClassesService.update).toHaveBeenCalledWith(sisyphusClasses);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackSisyphusSubJobById', () => {
        it('Should return tracked SisyphusSubJob primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackSisyphusSubJobById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});
