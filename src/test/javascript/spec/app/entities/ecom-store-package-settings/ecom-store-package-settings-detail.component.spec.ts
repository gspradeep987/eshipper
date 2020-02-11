import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStorePackageSettingsDetailComponent } from 'app/entities/ecom-store-package-settings/ecom-store-package-settings-detail.component';
import { EcomStorePackageSettings } from 'app/shared/model/ecom-store-package-settings.model';

describe('Component Tests', () => {
  describe('EcomStorePackageSettings Management Detail Component', () => {
    let comp: EcomStorePackageSettingsDetailComponent;
    let fixture: ComponentFixture<EcomStorePackageSettingsDetailComponent>;
    const route = ({ data: of({ ecomStorePackageSettings: new EcomStorePackageSettings(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStorePackageSettingsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomStorePackageSettingsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStorePackageSettingsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomStorePackageSettings on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomStorePackageSettings).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
