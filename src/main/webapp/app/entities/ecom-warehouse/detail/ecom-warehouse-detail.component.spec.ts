import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomWarehouseDetailComponent } from './ecom-warehouse-detail.component';

describe('Component Tests', () => {
  describe('EcomWarehouse Management Detail Component', () => {
    let comp: EcomWarehouseDetailComponent;
    let fixture: ComponentFixture<EcomWarehouseDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomWarehouseDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomWarehouse: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(EcomWarehouseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomWarehouseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomWarehouse on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomWarehouse).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
