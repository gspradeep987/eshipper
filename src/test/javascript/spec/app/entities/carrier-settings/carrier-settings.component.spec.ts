import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { CarrierSettingsComponent } from 'app/entities/carrier-settings/carrier-settings.component';
import { CarrierSettingsService } from 'app/entities/carrier-settings/carrier-settings.service';
import { CarrierSettings } from 'app/shared/model/carrier-settings.model';

describe('Component Tests', () => {
  describe('CarrierSettings Management Component', () => {
    let comp: CarrierSettingsComponent;
    let fixture: ComponentFixture<CarrierSettingsComponent>;
    let service: CarrierSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CarrierSettingsComponent]
      })
        .overrideTemplate(CarrierSettingsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CarrierSettingsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CarrierSettingsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CarrierSettings(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.carrierSettings && comp.carrierSettings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
