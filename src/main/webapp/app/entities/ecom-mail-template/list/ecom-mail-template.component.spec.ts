import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomMailTemplateService } from '../service/ecom-mail-template.service';

import { EcomMailTemplateComponent } from './ecom-mail-template.component';

describe('Component Tests', () => {
  describe('EcomMailTemplate Management Component', () => {
    let comp: EcomMailTemplateComponent;
    let fixture: ComponentFixture<EcomMailTemplateComponent>;
    let service: EcomMailTemplateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomMailTemplateComponent],
      })
        .overrideTemplate(EcomMailTemplateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMailTemplateComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomMailTemplateService);

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
      expect(comp.ecomMailTemplates?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
