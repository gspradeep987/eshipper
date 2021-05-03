import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomStoreColorThemeDetailComponent } from './ecom-store-color-theme-detail.component';

describe('Component Tests', () => {
  describe('EcomStoreColorTheme Management Detail Component', () => {
    let comp: EcomStoreColorThemeDetailComponent;
    let fixture: ComponentFixture<EcomStoreColorThemeDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomStoreColorThemeDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomStoreColorTheme: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(EcomStoreColorThemeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStoreColorThemeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomStoreColorTheme on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomStoreColorTheme).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
