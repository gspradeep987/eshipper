import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesCommissionDetailsDetailComponent } from 'app/entities/wo-sales-commission-details/wo-sales-commission-details-detail.component';
import { WoSalesCommissionDetails } from 'app/shared/model/wo-sales-commission-details.model';

describe('Component Tests', () => {
  describe('WoSalesCommissionDetails Management Detail Component', () => {
    let comp: WoSalesCommissionDetailsDetailComponent;
    let fixture: ComponentFixture<WoSalesCommissionDetailsDetailComponent>;
    const route = ({ data: of({ woSalesCommissionDetails: new WoSalesCommissionDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesCommissionDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(WoSalesCommissionDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoSalesCommissionDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load woSalesCommissionDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woSalesCommissionDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
