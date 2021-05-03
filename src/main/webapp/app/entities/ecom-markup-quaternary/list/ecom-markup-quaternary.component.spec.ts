import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomMarkupQuaternaryService } from '../service/ecom-markup-quaternary.service';

import { EcomMarkupQuaternaryComponent } from './ecom-markup-quaternary.component';

describe('Component Tests', () => {
  describe('EcomMarkupQuaternary Management Component', () => {
    let comp: EcomMarkupQuaternaryComponent;
    let fixture: ComponentFixture<EcomMarkupQuaternaryComponent>;
    let service: EcomMarkupQuaternaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomMarkupQuaternaryComponent],
      })
        .overrideTemplate(EcomMarkupQuaternaryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupQuaternaryComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomMarkupQuaternaryService);

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
      expect(comp.ecomMarkupQuaternaries?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
