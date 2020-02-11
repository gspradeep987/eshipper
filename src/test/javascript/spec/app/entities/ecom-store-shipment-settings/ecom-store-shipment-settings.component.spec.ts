import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreShipmentSettingsComponent } from 'app/entities/ecom-store-shipment-settings/ecom-store-shipment-settings.component';
import { EcomStoreShipmentSettingsService } from 'app/entities/ecom-store-shipment-settings/ecom-store-shipment-settings.service';
import { EcomStoreShipmentSettings } from 'app/shared/model/ecom-store-shipment-settings.model';

describe('Component Tests', () => {
  describe('EcomStoreShipmentSettings Management Component', () => {
    let comp: EcomStoreShipmentSettingsComponent;
    let fixture: ComponentFixture<EcomStoreShipmentSettingsComponent>;
    let service: EcomStoreShipmentSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreShipmentSettingsComponent]
      })
        .overrideTemplate(EcomStoreShipmentSettingsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreShipmentSettingsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreShipmentSettingsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomStoreShipmentSettings(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomStoreShipmentSettings && comp.ecomStoreShipmentSettings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
