import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomProductImageDetailComponent } from './ecom-product-image-detail.component';

describe('Component Tests', () => {
  describe('EcomProductImage Management Detail Component', () => {
    let comp: EcomProductImageDetailComponent;
    let fixture: ComponentFixture<EcomProductImageDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomProductImageDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomProductImage: { id: 123 } }) },
          },
        ],
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
