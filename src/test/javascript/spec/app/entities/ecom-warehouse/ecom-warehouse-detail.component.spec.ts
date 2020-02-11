import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomWarehouseDetailComponent } from 'app/entities/ecom-warehouse/ecom-warehouse-detail.component';
import { EcomWarehouse } from 'app/shared/model/ecom-warehouse.model';

describe('Component Tests', () => {
  describe('EcomWarehouse Management Detail Component', () => {
    let comp: EcomWarehouseDetailComponent;
    let fixture: ComponentFixture<EcomWarehouseDetailComponent>;
    const route = ({ data: of({ ecomWarehouse: new EcomWarehouse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomWarehouseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
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
