import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomStoreAddressDetailComponent } from './ecom-store-address-detail.component';

describe('Component Tests', () => {
  describe('EcomStoreAddress Management Detail Component', () => {
    let comp: EcomStoreAddressDetailComponent;
    let fixture: ComponentFixture<EcomStoreAddressDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomStoreAddressDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomStoreAddress: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(EcomStoreAddressDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStoreAddressDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomStoreAddress on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomStoreAddress).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
