import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomStorePackageSettingsService } from '../service/ecom-store-package-settings.service';

import { EcomStorePackageSettingsComponent } from './ecom-store-package-settings.component';

describe('Component Tests', () => {
  describe('EcomStorePackageSettings Management Component', () => {
    let comp: EcomStorePackageSettingsComponent;
    let fixture: ComponentFixture<EcomStorePackageSettingsComponent>;
    let service: EcomStorePackageSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStorePackageSettingsComponent],
      })
        .overrideTemplate(EcomStorePackageSettingsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStorePackageSettingsComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomStorePackageSettingsService);

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
      expect(comp.ecomStorePackageSettings?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
