import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomStoreShipmentSettingsService } from '../service/ecom-store-shipment-settings.service';

import { EcomStoreShipmentSettingsComponent } from './ecom-store-shipment-settings.component';

describe('Component Tests', () => {
  describe('EcomStoreShipmentSettings Management Component', () => {
    let comp: EcomStoreShipmentSettingsComponent;
    let fixture: ComponentFixture<EcomStoreShipmentSettingsComponent>;
    let service: EcomStoreShipmentSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStoreShipmentSettingsComponent],
      })
        .overrideTemplate(EcomStoreShipmentSettingsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreShipmentSettingsComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomStoreShipmentSettingsService);

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
      expect(comp.ecomStoreShipmentSettings?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
