import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreColorThemeComponent } from 'app/entities/ecom-store-color-theme/ecom-store-color-theme.component';
import { EcomStoreColorThemeService } from 'app/entities/ecom-store-color-theme/ecom-store-color-theme.service';
import { EcomStoreColorTheme } from 'app/shared/model/ecom-store-color-theme.model';

describe('Component Tests', () => {
  describe('EcomStoreColorTheme Management Component', () => {
    let comp: EcomStoreColorThemeComponent;
    let fixture: ComponentFixture<EcomStoreColorThemeComponent>;
    let service: EcomStoreColorThemeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreColorThemeComponent]
      })
        .overrideTemplate(EcomStoreColorThemeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreColorThemeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreColorThemeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomStoreColorTheme(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomStoreColorThemes && comp.ecomStoreColorThemes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
