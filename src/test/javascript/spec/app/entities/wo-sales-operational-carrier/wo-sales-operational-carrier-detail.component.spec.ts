import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesOperationalCarrierDetailComponent } from 'app/entities/wo-sales-operational-carrier/wo-sales-operational-carrier-detail.component';
import { WoSalesOperationalCarrier } from 'app/shared/model/wo-sales-operational-carrier.model';

describe('Component Tests', () => {
  describe('WoSalesOperationalCarrier Management Detail Component', () => {
    let comp: WoSalesOperationalCarrierDetailComponent;
    let fixture: ComponentFixture<WoSalesOperationalCarrierDetailComponent>;
    const route = ({ data: of({ woSalesOperationalCarrier: new WoSalesOperationalCarrier(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesOperationalCarrierDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WoSalesOperationalCarrierDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoSalesOperationalCarrierDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load woSalesOperationalCarrier on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woSalesOperationalCarrier).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
