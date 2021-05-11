jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { SisyphusClientService } from '../service/sisyphus-client.service';
import { ISisyphusClient, SisyphusClient } from '../sisyphus-client.model';
import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';
import { SisyphusJobService } from 'app/entities/sisyphus-job/service/sisyphus-job.service';

import { SisyphusClientUpdateComponent } from './sisyphus-client-update.component';

describe('Component Tests', () => {
  describe('SisyphusClient Management Update Component', () => {
    let comp: SisyphusClientUpdateComponent;
    let fixture: ComponentFixture<SisyphusClientUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let sisyphusClientService: SisyphusClientService;
    let sisyphusJobService: SisyphusJobService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [SisyphusClientUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(SisyphusClientUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SisyphusClientUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      sisyphusClientService = TestBed.inject(SisyphusClientService);
      sisyphusJobService = TestBed.inject(SisyphusJobService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call SisyphusJob query and add missing value', () => {
        const sisyphusClient: ISisyphusClient = { id: 456 };
        const sisyphusClient: ISisyphusJob = { id: 27840 };
        sisyphusClient.sisyphusClient = sisyphusClient;

        const sisyphusJobCollection: ISisyphusJob[] = [{ id: 10333 }];
        spyOn(sisyphusJobService, 'query').and.returnValue(of(new HttpResponse({ body: sisyphusJobCollection })));
        const additionalSisyphusJobs = [sisyphusClient];
        const expectedCollection: ISisyphusJob[] = [...additionalSisyphusJobs, ...sisyphusJobCollection];
        spyOn(sisyphusJobService, 'addSisyphusJobToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ sisyphusClient });
        comp.ngOnInit();

        expect(sisyphusJobService.query).toHaveBeenCalled();
        expect(sisyphusJobService.addSisyphusJobToCollectionIfMissing).toHaveBeenCalledWith(
          sisyphusJobCollection,
          ...additionalSisyphusJobs
        );
        expect(comp.sisyphusJobsSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const sisyphusClient: ISisyphusClient = { id: 456 };
        const sisyphusClient: ISisyphusJob = { id: 47516 };
        sisyphusClient.sisyphusClient = sisyphusClient;

        activatedRoute.data = of({ sisyphusClient });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(sisyphusClient));
        expect(comp.sisyphusJobsSharedCollection).toContain(sisyphusClient);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusClient = { id: 123 };
        spyOn(sisyphusClientService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusClient });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: sisyphusClient }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(sisyphusClientService.update).toHaveBeenCalledWith(sisyphusClient);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusClient = new SisyphusClient();
        spyOn(sisyphusClientService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusClient });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: sisyphusClient }));
        saveSubject.complete();

        // THEN
        expect(sisyphusClientService.create).toHaveBeenCalledWith(sisyphusClient);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusClient = { id: 123 };
        spyOn(sisyphusClientService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusClient });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(sisyphusClientService.update).toHaveBeenCalledWith(sisyphusClient);
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
