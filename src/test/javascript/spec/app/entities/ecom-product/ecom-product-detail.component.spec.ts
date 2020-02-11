import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomProductDetailComponent } from 'app/entities/ecom-product/ecom-product-detail.component';
import { EcomProduct } from 'app/shared/model/ecom-product.model';

describe('Component Tests', () => {
  describe('EcomProduct Management Detail Component', () => {
    let comp: EcomProductDetailComponent;
    let fixture: ComponentFixture<EcomProductDetailComponent>;
    const route = ({ data: of({ ecomProduct: new EcomProduct(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomProductDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomProductDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomProductDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomProduct on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomProduct).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
