import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomMailTemplateComponent } from 'app/entities/ecom-mail-template/ecom-mail-template.component';
import { EcomMailTemplateService } from 'app/entities/ecom-mail-template/ecom-mail-template.service';
import { EcomMailTemplate } from 'app/shared/model/ecom-mail-template.model';

describe('Component Tests', () => {
  describe('EcomMailTemplate Management Component', () => {
    let comp: EcomMailTemplateComponent;
    let fixture: ComponentFixture<EcomMailTemplateComponent>;
    let service: EcomMailTemplateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMailTemplateComponent]
      })
        .overrideTemplate(EcomMailTemplateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMailTemplateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomMailTemplateService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomMailTemplate(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomMailTemplates && comp.ecomMailTemplates[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
