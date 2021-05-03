import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomProductImageService } from '../service/ecom-product-image.service';

import { EcomProductImageComponent } from './ecom-product-image.component';

describe('Component Tests', () => {
  describe('EcomProductImage Management Component', () => {
    let comp: EcomProductImageComponent;
    let fixture: ComponentFixture<EcomProductImageComponent>;
    let service: EcomProductImageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomProductImageComponent],
      })
        .overrideTemplate(EcomProductImageComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomProductImageComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomProductImageService);

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
      expect(comp.ecomProductImages?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
