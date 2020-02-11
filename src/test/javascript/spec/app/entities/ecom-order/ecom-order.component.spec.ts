import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderComponent } from 'app/entities/ecom-order/ecom-order.component';
import { EcomOrderService } from 'app/entities/ecom-order/ecom-order.service';
import { EcomOrder } from 'app/shared/model/ecom-order.model';

describe('Component Tests', () => {
  describe('EcomOrder Management Component', () => {
    let comp: EcomOrderComponent;
    let fixture: ComponentFixture<EcomOrderComponent>;
    let service: EcomOrderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderComponent]
      })
        .overrideTemplate(EcomOrderComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomOrderComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomOrderService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomOrder(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomOrders && comp.ecomOrders[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
