import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ElasticStatusUpdateComponent } from 'app/entities/elastic-status/elastic-status-update.component';
import { ElasticStatusService } from 'app/entities/elastic-status/elastic-status.service';
import { ElasticStatus } from 'app/shared/model/elastic-status.model';

describe('Component Tests', () => {
  describe('ElasticStatus Management Update Component', () => {
    let comp: ElasticStatusUpdateComponent;
    let fixture: ComponentFixture<ElasticStatusUpdateComponent>;
    let service: ElasticStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticStatusUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ElasticStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ElasticStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ElasticStatusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ElasticStatus(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ElasticStatus();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
