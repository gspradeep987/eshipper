import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesAgentDetailComponent } from 'app/entities/wo-sales-agent/wo-sales-agent-detail.component';
import { WoSalesAgent } from 'app/shared/model/wo-sales-agent.model';

describe('Component Tests', () => {
  describe('WoSalesAgent Management Detail Component', () => {
    let comp: WoSalesAgentDetailComponent;
    let fixture: ComponentFixture<WoSalesAgentDetailComponent>;
    const route = ({ data: of({ woSalesAgent: new WoSalesAgent(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesAgentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(WoSalesAgentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoSalesAgentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load woSalesAgent on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woSalesAgent).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
