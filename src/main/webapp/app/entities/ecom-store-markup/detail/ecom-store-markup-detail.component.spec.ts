import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomStoreMarkupDetailComponent } from './ecom-store-markup-detail.component';

describe('Component Tests', () => {
  describe('EcomStoreMarkup Management Detail Component', () => {
    let comp: EcomStoreMarkupDetailComponent;
    let fixture: ComponentFixture<EcomStoreMarkupDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomStoreMarkupDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomStoreMarkup: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(EcomStoreMarkupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStoreMarkupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomStoreMarkup on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomStoreMarkup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
