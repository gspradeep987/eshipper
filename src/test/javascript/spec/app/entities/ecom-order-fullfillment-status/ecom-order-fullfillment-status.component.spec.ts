import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderFullfillmentStatusComponent } from 'app/entities/ecom-order-fullfillment-status/ecom-order-fullfillment-status.component';
import { EcomOrderFullfillmentStatusService } from 'app/entities/ecom-order-fullfillment-status/ecom-order-fullfillment-status.service';
import { EcomOrderFullfillmentStatus } from 'app/shared/model/ecom-order-fullfillment-status.model';

describe('Component Tests', () => {
  describe('EcomOrderFullfillmentStatus Management Component', () => {
    let comp: EcomOrderFullfillmentStatusComponent;
    let fixture: ComponentFixture<EcomOrderFullfillmentStatusComponent>;
    let service: EcomOrderFullfillmentStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderFullfillmentStatusComponent],
      })
        .overrideTemplate(EcomOrderFullfillmentStatusComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomOrderFullfillmentStatusComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomOrderFullfillmentStatusService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomOrderFullfillmentStatus(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomOrderFullfillmentStatuses && comp.ecomOrderFullfillmentStatuses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
