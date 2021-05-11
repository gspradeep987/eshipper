jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { SisyphusJobService } from '../service/sisyphus-job.service';
import { ISisyphusJob, SisyphusJob } from '../sisyphus-job.model';

import { SisyphusJobUpdateComponent } from './sisyphus-job-update.component';

describe('Component Tests', () => {
  describe('SisyphusJob Management Update Component', () => {
    let comp: SisyphusJobUpdateComponent;
    let fixture: ComponentFixture<SisyphusJobUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let sisyphusJobService: SisyphusJobService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [SisyphusJobUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(SisyphusJobUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SisyphusJobUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      sisyphusJobService = TestBed.inject(SisyphusJobService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const sisyphusJob: ISisyphusJob = { id: 456 };

        activatedRoute.data = of({ sisyphusJob });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(sisyphusJob));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusJob = { id: 123 };
        spyOn(sisyphusJobService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusJob });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: sisyphusJob }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(sisyphusJobService.update).toHaveBeenCalledWith(sisyphusJob);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusJob = new SisyphusJob();
        spyOn(sisyphusJobService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusJob });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: sisyphusJob }));
        saveSubject.complete();

        // THEN
        expect(sisyphusJobService.create).toHaveBeenCalledWith(sisyphusJob);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const sisyphusJob = { id: 123 };
        spyOn(sisyphusJobService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ sisyphusJob });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(sisyphusJobService.update).toHaveBeenCalledWith(sisyphusJob);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
