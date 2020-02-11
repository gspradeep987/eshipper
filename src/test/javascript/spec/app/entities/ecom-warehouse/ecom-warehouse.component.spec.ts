import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomWarehouseComponent } from 'app/entities/ecom-warehouse/ecom-warehouse.component';
import { EcomWarehouseService } from 'app/entities/ecom-warehouse/ecom-warehouse.service';
import { EcomWarehouse } from 'app/shared/model/ecom-warehouse.model';

describe('Component Tests', () => {
  describe('EcomWarehouse Management Component', () => {
    let comp: EcomWarehouseComponent;
    let fixture: ComponentFixture<EcomWarehouseComponent>;
    let service: EcomWarehouseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomWarehouseComponent]
      })
        .overrideTemplate(EcomWarehouseComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomWarehouseComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomWarehouseService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomWarehouse(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomWarehouses && comp.ecomWarehouses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
