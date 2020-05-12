import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesCommissionTransferDetailComponent } from 'app/entities/wo-sales-commission-transfer/wo-sales-commission-transfer-detail.component';
import { WoSalesCommissionTransfer } from 'app/shared/model/wo-sales-commission-transfer.model';

describe('Component Tests', () => {
  describe('WoSalesCommissionTransfer Management Detail Component', () => {
    let comp: WoSalesCommissionTransferDetailComponent;
    let fixture: ComponentFixture<WoSalesCommissionTransferDetailComponent>;
    const route = ({ data: of({ woSalesCommissionTransfer: new WoSalesCommissionTransfer(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesCommissionTransferDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WoSalesCommissionTransferDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoSalesCommissionTransferDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load woSalesCommissionTransfer on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woSalesCommissionTransfer).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
