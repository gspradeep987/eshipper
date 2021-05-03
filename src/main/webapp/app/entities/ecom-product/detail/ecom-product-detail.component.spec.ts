import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomProductDetailComponent } from './ecom-product-detail.component';

describe('Component Tests', () => {
  describe('EcomProduct Management Detail Component', () => {
    let comp: EcomProductDetailComponent;
    let fixture: ComponentFixture<EcomProductDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomProductDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomProduct: { id: 123 } }) },
          },
        ],
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
