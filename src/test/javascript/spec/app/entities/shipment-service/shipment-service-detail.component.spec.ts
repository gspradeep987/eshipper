import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ShipmentServiceDetailComponent } from 'app/entities/shipment-service/shipment-service-detail.component';
import { ShipmentService } from 'app/shared/model/shipment-service.model';

describe('Component Tests', () => {
  describe('ShipmentService Management Detail Component', () => {
    let comp: ShipmentServiceDetailComponent;
    let fixture: ComponentFixture<ShipmentServiceDetailComponent>;
    const route = ({ data: of({ shipmentService: new ShipmentService(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ShipmentServiceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
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
