import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { ShipmentServiceComponent } from 'app/entities/shipment-service/shipment-service.component';
import { ShipmentServiceService } from 'app/entities/shipment-service/shipment-service.service';
import { ShipmentService } from 'app/shared/model/shipment-service.model';

describe('Component Tests', () => {
  describe('ShipmentService Management Component', () => {
    let comp: ShipmentServiceComponent;
    let fixture: ComponentFixture<ShipmentServiceComponent>;
    let service: ShipmentServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ShipmentServiceComponent]
      })
        .overrideTemplate(ShipmentServiceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShipmentServiceComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ShipmentServiceService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ShipmentService(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.shipmentServices && comp.shipmentServices[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
