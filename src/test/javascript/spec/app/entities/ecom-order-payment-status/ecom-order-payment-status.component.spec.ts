import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderPaymentStatusComponent } from 'app/entities/ecom-order-payment-status/ecom-order-payment-status.component';
import { EcomOrderPaymentStatusService } from 'app/entities/ecom-order-payment-status/ecom-order-payment-status.service';
import { EcomOrderPaymentStatus } from 'app/shared/model/ecom-order-payment-status.model';

describe('Component Tests', () => {
  describe('EcomOrderPaymentStatus Management Component', () => {
    let comp: EcomOrderPaymentStatusComponent;
    let fixture: ComponentFixture<EcomOrderPaymentStatusComponent>;
    let service: EcomOrderPaymentStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderPaymentStatusComponent],
      })
        .overrideTemplate(EcomOrderPaymentStatusComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomOrderPaymentStatusComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomOrderPaymentStatusService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomOrderPaymentStatus(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomOrderPaymentStatuses && comp.ecomOrderPaymentStatuses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
