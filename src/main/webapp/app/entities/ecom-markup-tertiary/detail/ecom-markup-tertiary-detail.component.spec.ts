import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomMarkupTertiaryDetailComponent } from './ecom-markup-tertiary-detail.component';

describe('Component Tests', () => {
  describe('EcomMarkupTertiary Management Detail Component', () => {
    let comp: EcomMarkupTertiaryDetailComponent;
    let fixture: ComponentFixture<EcomMarkupTertiaryDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomMarkupTertiaryDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomMarkupTertiary: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(EcomMarkupTertiaryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomMarkupTertiaryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomMarkupTertiary on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomMarkupTertiary).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
