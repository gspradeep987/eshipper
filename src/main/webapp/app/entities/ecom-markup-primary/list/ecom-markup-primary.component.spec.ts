import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomMarkupPrimaryService } from '../service/ecom-markup-primary.service';

import { EcomMarkupPrimaryComponent } from './ecom-markup-primary.component';

describe('Component Tests', () => {
  describe('EcomMarkupPrimary Management Component', () => {
    let comp: EcomMarkupPrimaryComponent;
    let fixture: ComponentFixture<EcomMarkupPrimaryComponent>;
    let service: EcomMarkupPrimaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomMarkupPrimaryComponent],
      })
        .overrideTemplate(EcomMarkupPrimaryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupPrimaryComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomMarkupPrimaryService);

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
      expect(comp.ecomMarkupPrimaries?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
