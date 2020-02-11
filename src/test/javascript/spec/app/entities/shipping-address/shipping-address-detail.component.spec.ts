import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ShippingAddressDetailComponent } from 'app/entities/shipping-address/shipping-address-detail.component';
import { ShippingAddress } from 'app/shared/model/shipping-address.model';

describe('Component Tests', () => {
  describe('ShippingAddress Management Detail Component', () => {
    let comp: ShippingAddressDetailComponent;
    let fixture: ComponentFixture<ShippingAddressDetailComponent>;
    const route = ({ data: of({ shippingAddress: new ShippingAddress(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ShippingAddressDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
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
