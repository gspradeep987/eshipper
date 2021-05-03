import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomMarkupQuaternaryDetailComponent } from './ecom-markup-quaternary-detail.component';

describe('Component Tests', () => {
  describe('EcomMarkupQuaternary Management Detail Component', () => {
    let comp: EcomMarkupQuaternaryDetailComponent;
    let fixture: ComponentFixture<EcomMarkupQuaternaryDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomMarkupQuaternaryDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomMarkupQuaternary: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(EcomMarkupQuaternaryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomMarkupQuaternaryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomMarkupQuaternary on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomMarkupQuaternary).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
