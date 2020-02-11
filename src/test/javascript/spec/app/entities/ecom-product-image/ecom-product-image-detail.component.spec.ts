import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomProductImageDetailComponent } from 'app/entities/ecom-product-image/ecom-product-image-detail.component';
import { EcomProductImage } from 'app/shared/model/ecom-product-image.model';

describe('Component Tests', () => {
  describe('EcomProductImage Management Detail Component', () => {
    let comp: EcomProductImageDetailComponent;
    let fixture: ComponentFixture<EcomProductImageDetailComponent>;
    const route = ({ data: of({ ecomProductImage: new EcomProductImage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomProductImageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomProductImageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomProductImageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomProductImage on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomProductImage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
