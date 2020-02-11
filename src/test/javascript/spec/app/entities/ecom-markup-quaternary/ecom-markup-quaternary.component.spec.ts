import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomMarkupQuaternaryComponent } from 'app/entities/ecom-markup-quaternary/ecom-markup-quaternary.component';
import { EcomMarkupQuaternaryService } from 'app/entities/ecom-markup-quaternary/ecom-markup-quaternary.service';
import { EcomMarkupQuaternary } from 'app/shared/model/ecom-markup-quaternary.model';

describe('Component Tests', () => {
  describe('EcomMarkupQuaternary Management Component', () => {
    let comp: EcomMarkupQuaternaryComponent;
    let fixture: ComponentFixture<EcomMarkupQuaternaryComponent>;
    let service: EcomMarkupQuaternaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMarkupQuaternaryComponent]
      })
        .overrideTemplate(EcomMarkupQuaternaryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupQuaternaryComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomMarkupQuaternaryService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomMarkupQuaternary(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomMarkupQuaternaries && comp.ecomMarkupQuaternaries[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
