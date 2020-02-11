import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomProductComponent } from 'app/entities/ecom-product/ecom-product.component';
import { EcomProductService } from 'app/entities/ecom-product/ecom-product.service';
import { EcomProduct } from 'app/shared/model/ecom-product.model';

describe('Component Tests', () => {
  describe('EcomProduct Management Component', () => {
    let comp: EcomProductComponent;
    let fixture: ComponentFixture<EcomProductComponent>;
    let service: EcomProductService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomProductComponent]
      })
        .overrideTemplate(EcomProductComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomProductComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomProductService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomProduct(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomProducts && comp.ecomProducts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
