import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomMarkupPrimaryComponent } from 'app/entities/ecom-markup-primary/ecom-markup-primary.component';
import { EcomMarkupPrimaryService } from 'app/entities/ecom-markup-primary/ecom-markup-primary.service';
import { EcomMarkupPrimary } from 'app/shared/model/ecom-markup-primary.model';

describe('Component Tests', () => {
  describe('EcomMarkupPrimary Management Component', () => {
    let comp: EcomMarkupPrimaryComponent;
    let fixture: ComponentFixture<EcomMarkupPrimaryComponent>;
    let service: EcomMarkupPrimaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMarkupPrimaryComponent]
      })
        .overrideTemplate(EcomMarkupPrimaryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupPrimaryComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomMarkupPrimaryService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomMarkupPrimary(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomMarkupPrimaries && comp.ecomMarkupPrimaries[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
