import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreAddressDetailComponent } from 'app/entities/ecom-store-address/ecom-store-address-detail.component';
import { EcomStoreAddress } from 'app/shared/model/ecom-store-address.model';

describe('Component Tests', () => {
  describe('EcomStoreAddress Management Detail Component', () => {
    let comp: EcomStoreAddressDetailComponent;
    let fixture: ComponentFixture<EcomStoreAddressDetailComponent>;
    const route = ({ data: of({ ecomStoreAddress: new EcomStoreAddress(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreAddressDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomStoreAddressDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStoreAddressDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomStoreAddress on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomStoreAddress).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
