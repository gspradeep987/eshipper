import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomMarkupTertiaryComponent } from 'app/entities/ecom-markup-tertiary/ecom-markup-tertiary.component';
import { EcomMarkupTertiaryService } from 'app/entities/ecom-markup-tertiary/ecom-markup-tertiary.service';
import { EcomMarkupTertiary } from 'app/shared/model/ecom-markup-tertiary.model';

describe('Component Tests', () => {
  describe('EcomMarkupTertiary Management Component', () => {
    let comp: EcomMarkupTertiaryComponent;
    let fixture: ComponentFixture<EcomMarkupTertiaryComponent>;
    let service: EcomMarkupTertiaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMarkupTertiaryComponent]
      })
        .overrideTemplate(EcomMarkupTertiaryComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupTertiaryComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomMarkupTertiaryService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomMarkupTertiary(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomMarkupTertiaries && comp.ecomMarkupTertiaries[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
