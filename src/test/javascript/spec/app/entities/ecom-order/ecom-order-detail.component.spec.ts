import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderDetailComponent } from 'app/entities/ecom-order/ecom-order-detail.component';
import { EcomOrder } from 'app/shared/model/ecom-order.model';

describe('Component Tests', () => {
  describe('EcomOrder Management Detail Component', () => {
    let comp: EcomOrderDetailComponent;
    let fixture: ComponentFixture<EcomOrderDetailComponent>;
    const route = ({ data: of({ ecomOrder: new EcomOrder(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomOrderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomOrderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomOrder on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomOrder).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
