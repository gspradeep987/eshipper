import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomProductImageComponent } from 'app/entities/ecom-product-image/ecom-product-image.component';
import { EcomProductImageService } from 'app/entities/ecom-product-image/ecom-product-image.service';
import { EcomProductImage } from 'app/shared/model/ecom-product-image.model';

describe('Component Tests', () => {
  describe('EcomProductImage Management Component', () => {
    let comp: EcomProductImageComponent;
    let fixture: ComponentFixture<EcomProductImageComponent>;
    let service: EcomProductImageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomProductImageComponent]
      })
        .overrideTemplate(EcomProductImageComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomProductImageComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomProductImageService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomProductImage(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomProductImages && comp.ecomProductImages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
