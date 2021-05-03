import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomWarehouseService } from '../service/ecom-warehouse.service';

import { EcomWarehouseComponent } from './ecom-warehouse.component';

describe('Component Tests', () => {
  describe('EcomWarehouse Management Component', () => {
    let comp: EcomWarehouseComponent;
    let fixture: ComponentFixture<EcomWarehouseComponent>;
    let service: EcomWarehouseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomWarehouseComponent],
      })
        .overrideTemplate(EcomWarehouseComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomWarehouseComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomWarehouseService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ id: 123 }],
            headers,
          })
        )
      );
    });

    it('Should call load all on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomWarehouses?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
