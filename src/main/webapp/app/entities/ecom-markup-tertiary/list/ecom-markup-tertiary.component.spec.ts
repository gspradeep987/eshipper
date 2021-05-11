import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomMarkupTertiaryService } from '../service/ecom-markup-tertiary.service';

import { EcomMarkupTertiaryComponent } from './ecom-markup-tertiary.component';

describe('Component Tests', () => {
  describe('EcomMarkupTertiary Management Component', () => {
    let comp: EcomMarkupTertiaryComponent;
    let fixture: ComponentFixture<EcomMarkupTertiaryComponent>;
    let service: EcomMarkupTertiaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomMarkupTertiaryComponent],
      })
        .overrideTemplate(EcomMarkupTertiaryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupTertiaryComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomMarkupTertiaryService);

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
      expect(comp.ecomMarkupTertiaries?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
