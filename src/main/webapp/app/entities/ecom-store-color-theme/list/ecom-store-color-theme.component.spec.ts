import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomStoreColorThemeService } from '../service/ecom-store-color-theme.service';

import { EcomStoreColorThemeComponent } from './ecom-store-color-theme.component';

describe('Component Tests', () => {
  describe('EcomStoreColorTheme Management Component', () => {
    let comp: EcomStoreColorThemeComponent;
    let fixture: ComponentFixture<EcomStoreColorThemeComponent>;
    let service: EcomStoreColorThemeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStoreColorThemeComponent],
      })
        .overrideTemplate(EcomStoreColorThemeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreColorThemeComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomStoreColorThemeService);

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
      expect(comp.ecomStoreColorThemes?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
