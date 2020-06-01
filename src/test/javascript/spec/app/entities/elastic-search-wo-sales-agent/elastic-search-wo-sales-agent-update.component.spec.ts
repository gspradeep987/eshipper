import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ElasticSearchWoSalesAgentUpdateComponent } from 'app/entities/elastic-search-wo-sales-agent/elastic-search-wo-sales-agent-update.component';
import { ElasticSearchWoSalesAgentService } from 'app/entities/elastic-search-wo-sales-agent/elastic-search-wo-sales-agent.service';
import { ElasticSearchWoSalesAgent } from 'app/shared/model/elastic-search-wo-sales-agent.model';

describe('Component Tests', () => {
  describe('ElasticSearchWoSalesAgent Management Update Component', () => {
    let comp: ElasticSearchWoSalesAgentUpdateComponent;
    let fixture: ComponentFixture<ElasticSearchWoSalesAgentUpdateComponent>;
    let service: ElasticSearchWoSalesAgentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticSearchWoSalesAgentUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(ElasticSearchWoSalesAgentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ElasticSearchWoSalesAgentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ElasticSearchWoSalesAgentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ElasticSearchWoSalesAgent(123);
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
        const entity = new ElasticSearchWoSalesAgent();
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
