import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ShippingAddressDetailComponent } from './shipping-address-detail.component';

describe('Component Tests', () => {
  describe('ShippingAddress Management Detail Component', () => {
    let comp: ShippingAddressDetailComponent;
    let fixture: ComponentFixture<ShippingAddressDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [ShippingAddressDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ shippingAddress: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(ShippingAddressDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ShippingAddressDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load shippingAddress on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.shippingAddress).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
