import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ElasticSearchWoSalesAgentDetailComponent } from 'app/entities/elastic-search-wo-sales-agent/elastic-search-wo-sales-agent-detail.component';
import { ElasticSearchWoSalesAgent } from 'app/shared/model/elastic-search-wo-sales-agent.model';

describe('Component Tests', () => {
  describe('ElasticSearchWoSalesAgent Management Detail Component', () => {
    let comp: ElasticSearchWoSalesAgentDetailComponent;
    let fixture: ComponentFixture<ElasticSearchWoSalesAgentDetailComponent>;
    const route = ({ data: of({ elasticSearchWoSalesAgent: new ElasticSearchWoSalesAgent(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticSearchWoSalesAgentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ElasticSearchWoSalesAgentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ElasticSearchWoSalesAgentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load elasticSearchWoSalesAgent on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.elasticSearchWoSalesAgent).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
