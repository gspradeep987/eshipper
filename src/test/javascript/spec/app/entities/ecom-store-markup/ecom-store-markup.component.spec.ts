import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreMarkupComponent } from 'app/entities/ecom-store-markup/ecom-store-markup.component';
import { EcomStoreMarkupService } from 'app/entities/ecom-store-markup/ecom-store-markup.service';
import { EcomStoreMarkup } from 'app/shared/model/ecom-store-markup.model';

describe('Component Tests', () => {
  describe('EcomStoreMarkup Management Component', () => {
    let comp: EcomStoreMarkupComponent;
    let fixture: ComponentFixture<EcomStoreMarkupComponent>;
    let service: EcomStoreMarkupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreMarkupComponent]
      })
        .overrideTemplate(EcomStoreMarkupComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreMarkupComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreMarkupService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomStoreMarkup(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomStoreMarkups && comp.ecomStoreMarkups[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
