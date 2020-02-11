import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomMarkupSecondaryComponent } from 'app/entities/ecom-markup-secondary/ecom-markup-secondary.component';
import { EcomMarkupSecondaryService } from 'app/entities/ecom-markup-secondary/ecom-markup-secondary.service';
import { EcomMarkupSecondary } from 'app/shared/model/ecom-markup-secondary.model';

describe('Component Tests', () => {
  describe('EcomMarkupSecondary Management Component', () => {
    let comp: EcomMarkupSecondaryComponent;
    let fixture: ComponentFixture<EcomMarkupSecondaryComponent>;
    let service: EcomMarkupSecondaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMarkupSecondaryComponent]
      })
        .overrideTemplate(EcomMarkupSecondaryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupSecondaryComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomMarkupSecondaryService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomMarkupSecondary(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomMarkupSecondaries && comp.ecomMarkupSecondaries[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
