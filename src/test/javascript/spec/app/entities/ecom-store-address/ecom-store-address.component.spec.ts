import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreAddressComponent } from 'app/entities/ecom-store-address/ecom-store-address.component';
import { EcomStoreAddressService } from 'app/entities/ecom-store-address/ecom-store-address.service';
import { EcomStoreAddress } from 'app/shared/model/ecom-store-address.model';

describe('Component Tests', () => {
  describe('EcomStoreAddress Management Component', () => {
    let comp: EcomStoreAddressComponent;
    let fixture: ComponentFixture<EcomStoreAddressComponent>;
    let service: EcomStoreAddressService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreAddressComponent]
      })
        .overrideTemplate(EcomStoreAddressComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreAddressComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreAddressService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomStoreAddress(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomStoreAddresses && comp.ecomStoreAddresses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
