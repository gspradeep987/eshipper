import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CarrierSettingsDetailComponent } from 'app/entities/carrier-settings/carrier-settings-detail.component';
import { CarrierSettings } from 'app/shared/model/carrier-settings.model';

describe('Component Tests', () => {
  describe('CarrierSettings Management Detail Component', () => {
    let comp: CarrierSettingsDetailComponent;
    let fixture: ComponentFixture<CarrierSettingsDetailComponent>;
    const route = ({ data: of({ carrierSettings: new CarrierSettings(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CarrierSettingsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CarrierSettingsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CarrierSettingsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load carrierSettings on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.carrierSettings).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
