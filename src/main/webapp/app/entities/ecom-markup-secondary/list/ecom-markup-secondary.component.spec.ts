import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomMarkupSecondaryService } from '../service/ecom-markup-secondary.service';

import { EcomMarkupSecondaryComponent } from './ecom-markup-secondary.component';

describe('Component Tests', () => {
  describe('EcomMarkupSecondary Management Component', () => {
    let comp: EcomMarkupSecondaryComponent;
    let fixture: ComponentFixture<EcomMarkupSecondaryComponent>;
    let service: EcomMarkupSecondaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomMarkupSecondaryComponent],
      })
        .overrideTemplate(EcomMarkupSecondaryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupSecondaryComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomMarkupSecondaryService);

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
      expect(comp.ecomMarkupSecondaries?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
