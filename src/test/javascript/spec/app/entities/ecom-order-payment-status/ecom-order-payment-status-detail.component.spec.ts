import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderPaymentStatusDetailComponent } from 'app/entities/ecom-order-payment-status/ecom-order-payment-status-detail.component';
import { EcomOrderPaymentStatus } from 'app/shared/model/ecom-order-payment-status.model';

describe('Component Tests', () => {
  describe('EcomOrderPaymentStatus Management Detail Component', () => {
    let comp: EcomOrderPaymentStatusDetailComponent;
    let fixture: ComponentFixture<EcomOrderPaymentStatusDetailComponent>;
    const route = ({ data: of({ ecomOrderPaymentStatus: new EcomOrderPaymentStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderPaymentStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EcomOrderPaymentStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomOrderPaymentStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomOrderPaymentStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomOrderPaymentStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
