import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreShipmentSettingsDetailComponent } from 'app/entities/ecom-store-shipment-settings/ecom-store-shipment-settings-detail.component';
import { EcomStoreShipmentSettings } from 'app/shared/model/ecom-store-shipment-settings.model';

describe('Component Tests', () => {
  describe('EcomStoreShipmentSettings Management Detail Component', () => {
    let comp: EcomStoreShipmentSettingsDetailComponent;
    let fixture: ComponentFixture<EcomStoreShipmentSettingsDetailComponent>;
    const route = ({ data: of({ ecomStoreShipmentSettings: new EcomStoreShipmentSettings(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreShipmentSettingsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
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
