import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { ShipmentServiceService } from '../service/shipment-service.service';

import { ShipmentServiceComponent } from './shipment-service.component';

describe('Component Tests', () => {
  describe('ShipmentService Management Component', () => {
    let comp: ShipmentServiceComponent;
    let fixture: ComponentFixture<ShipmentServiceComponent>;
    let service: ShipmentServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [ShipmentServiceComponent],
      })
        .overrideTemplate(ShipmentServiceComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShipmentServiceComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(ShipmentServiceService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ id: 123 }],
            headers,
          })
        )
      );
    });

    it('Should call load all on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.shipmentServices?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
