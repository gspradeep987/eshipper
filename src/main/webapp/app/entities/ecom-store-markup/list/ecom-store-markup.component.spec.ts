import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomStoreMarkupService } from '../service/ecom-store-markup.service';

import { EcomStoreMarkupComponent } from './ecom-store-markup.component';

describe('Component Tests', () => {
  describe('EcomStoreMarkup Management Component', () => {
    let comp: EcomStoreMarkupComponent;
    let fixture: ComponentFixture<EcomStoreMarkupComponent>;
    let service: EcomStoreMarkupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStoreMarkupComponent],
      })
        .overrideTemplate(EcomStoreMarkupComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreMarkupComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomStoreMarkupService);

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
      expect(comp.ecomStoreMarkups?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
