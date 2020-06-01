import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap, Data } from '@angular/router';

import { EshipperTestModule } from '../../../test.module';
import { ElasticSearchWoSalesAgentComponent } from 'app/entities/elastic-search-wo-sales-agent/elastic-search-wo-sales-agent.component';
import { ElasticSearchWoSalesAgentService } from 'app/entities/elastic-search-wo-sales-agent/elastic-search-wo-sales-agent.service';
import { ElasticSearchWoSalesAgent } from 'app/shared/model/elastic-search-wo-sales-agent.model';

describe('Component Tests', () => {
  describe('ElasticSearchWoSalesAgent Management Component', () => {
    let comp: ElasticSearchWoSalesAgentComponent;
    let fixture: ComponentFixture<ElasticSearchWoSalesAgentComponent>;
    let service: ElasticSearchWoSalesAgentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticSearchWoSalesAgentComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0,
                    },
                  }),
              },
              queryParamMap: {
                subscribe: (fn: (value: Data) => void) =>
                  fn(
                    convertToParamMap({
                      page: '1',
                      size: '1',
                      sort: 'id,desc',
                    })
                  ),
              },
            },
          },
        ],
      })
        .overrideTemplate(ElasticSearchWoSalesAgentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ElasticSearchWoSalesAgentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ElasticSearchWoSalesAgentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ElasticSearchWoSalesAgent(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.elasticSearchWoSalesAgents && comp.elasticSearchWoSalesAgents[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ElasticSearchWoSalesAgent(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.elasticSearchWoSalesAgents && comp.elasticSearchWoSalesAgents[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
