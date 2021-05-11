import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomProductService } from '../service/ecom-product.service';

import { EcomProductComponent } from './ecom-product.component';

describe('Component Tests', () => {
  describe('EcomProduct Management Component', () => {
    let comp: EcomProductComponent;
    let fixture: ComponentFixture<EcomProductComponent>;
    let service: EcomProductService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomProductComponent],
      })
        .overrideTemplate(EcomProductComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomProductComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomProductService);

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
      expect(comp.ecomProducts?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
