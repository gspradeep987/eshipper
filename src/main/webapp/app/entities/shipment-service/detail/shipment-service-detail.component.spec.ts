import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShipmentServiceDetailComponent } from './shipment-service-detail.component';

describe('Component Tests', () => {
  describe('ShipmentService Management Detail Component', () => {
    let comp: ShipmentServiceDetailComponent;
    let fixture: ComponentFixture<ShipmentServiceDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [ShipmentServiceDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ shipmentService: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(ShipmentServiceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShipmentServiceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load shipmentService on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.shipmentService).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
