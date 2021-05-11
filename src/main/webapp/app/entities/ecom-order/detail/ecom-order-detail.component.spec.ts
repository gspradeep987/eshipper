import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomOrderDetailComponent } from './ecom-order-detail.component';

describe('Component Tests', () => {
  describe('EcomOrder Management Detail Component', () => {
    let comp: EcomOrderDetailComponent;
    let fixture: ComponentFixture<EcomOrderDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomOrderDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomOrder: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(EcomOrderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomOrderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomOrder on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomOrder).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
