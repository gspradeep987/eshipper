import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesOperationalDetailsDetailComponent } from 'app/entities/wo-sales-operational-details/wo-sales-operational-details-detail.component';
import { WoSalesOperationalDetails } from 'app/shared/model/wo-sales-operational-details.model';

describe('Component Tests', () => {
  describe('WoSalesOperationalDetails Management Detail Component', () => {
    let comp: WoSalesOperationalDetailsDetailComponent;
    let fixture: ComponentFixture<WoSalesOperationalDetailsDetailComponent>;
    const route = ({ data: of({ woSalesOperationalDetails: new WoSalesOperationalDetails(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesOperationalDetailsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(WoSalesOperationalDetailsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WoSalesOperationalDetailsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load woSalesOperationalDetails on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.woSalesOperationalDetails).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
