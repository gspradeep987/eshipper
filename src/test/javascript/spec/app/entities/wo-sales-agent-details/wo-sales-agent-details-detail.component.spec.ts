import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesAgentDetailsDetailComponent } from 'app/entities/wo-sales-agent-details/wo-sales-agent-details-detail.component';
import { WoSalesAgentDetails } from 'app/shared/model/wo-sales-agent-details.model';

describe('Component Tests', () => {
  describe('WoSalesAgentDetails Management Detail Component', () => {
    let comp: WoSalesAgentDetailsDetailComponent;
    let fixture: ComponentFixture<WoSalesAgentDetailsDetailComponent>;
    const route = ({ data: of({ woSalesAgentDetails: new WoSalesAgentDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesAgentDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(WoSalesAgentDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoSalesAgentDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load woSalesAgentDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woSalesAgentDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
