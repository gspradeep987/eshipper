import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomStoreShipmentSettingsDetailComponent } from './ecom-store-shipment-settings-detail.component';

describe('Component Tests', () => {
  describe('EcomStoreShipmentSettings Management Detail Component', () => {
    let comp: EcomStoreShipmentSettingsDetailComponent;
    let fixture: ComponentFixture<EcomStoreShipmentSettingsDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomStoreShipmentSettingsDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomStoreShipmentSettings: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(EcomStoreShipmentSettingsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStoreShipmentSettingsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomStoreShipmentSettings on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomStoreShipmentSettings).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
