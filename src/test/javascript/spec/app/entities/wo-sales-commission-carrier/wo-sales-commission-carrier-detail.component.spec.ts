import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesCommissionCarrierDetailComponent } from 'app/entities/wo-sales-commission-carrier/wo-sales-commission-carrier-detail.component';
import { WoSalesCommissionCarrier } from 'app/shared/model/wo-sales-commission-carrier.model';

describe('Component Tests', () => {
  describe('WoSalesCommissionCarrier Management Detail Component', () => {
    let comp: WoSalesCommissionCarrierDetailComponent;
    let fixture: ComponentFixture<WoSalesCommissionCarrierDetailComponent>;
    const route = ({ data: of({ woSalesCommissionCarrier: new WoSalesCommissionCarrier(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesCommissionCarrierDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(WoSalesCommissionCarrierDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoSalesCommissionCarrierDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load woSalesCommissionCarrier on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woSalesCommissionCarrier).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
