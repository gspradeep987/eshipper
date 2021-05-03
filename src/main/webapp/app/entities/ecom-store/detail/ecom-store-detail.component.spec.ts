import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomStoreDetailComponent } from './ecom-store-detail.component';

describe('Component Tests', () => {
  describe('EcomStore Management Detail Component', () => {
    let comp: EcomStoreDetailComponent;
    let fixture: ComponentFixture<EcomStoreDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomStoreDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomStore: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(EcomStoreDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStoreDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomStore on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomStore).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
