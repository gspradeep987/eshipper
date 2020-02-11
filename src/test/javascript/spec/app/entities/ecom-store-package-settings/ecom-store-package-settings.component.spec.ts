import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomStorePackageSettingsComponent } from 'app/entities/ecom-store-package-settings/ecom-store-package-settings.component';
import { EcomStorePackageSettingsService } from 'app/entities/ecom-store-package-settings/ecom-store-package-settings.service';
import { EcomStorePackageSettings } from 'app/shared/model/ecom-store-package-settings.model';

describe('Component Tests', () => {
  describe('EcomStorePackageSettings Management Component', () => {
    let comp: EcomStorePackageSettingsComponent;
    let fixture: ComponentFixture<EcomStorePackageSettingsComponent>;
    let service: EcomStorePackageSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStorePackageSettingsComponent]
      })
        .overrideTemplate(EcomStorePackageSettingsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStorePackageSettingsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStorePackageSettingsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomStorePackageSettings(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomStorePackageSettings && comp.ecomStorePackageSettings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
