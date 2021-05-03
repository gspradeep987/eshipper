import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomMarkupSecondaryDetailComponent } from './ecom-markup-secondary-detail.component';

describe('Component Tests', () => {
  describe('EcomMarkupSecondary Management Detail Component', () => {
    let comp: EcomMarkupSecondaryDetailComponent;
    let fixture: ComponentFixture<EcomMarkupSecondaryDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomMarkupSecondaryDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomMarkupSecondary: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(EcomMarkupSecondaryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomMarkupSecondaryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomMarkupSecondary on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomMarkupSecondary).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
