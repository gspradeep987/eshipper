import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomStoreAddressService } from '../service/ecom-store-address.service';

import { EcomStoreAddressComponent } from './ecom-store-address.component';

describe('Component Tests', () => {
  describe('EcomStoreAddress Management Component', () => {
    let comp: EcomStoreAddressComponent;
    let fixture: ComponentFixture<EcomStoreAddressComponent>;
    let service: EcomStoreAddressService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStoreAddressComponent],
      })
        .overrideTemplate(EcomStoreAddressComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreAddressComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomStoreAddressService);

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
      expect(comp.ecomStoreAddresses?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
