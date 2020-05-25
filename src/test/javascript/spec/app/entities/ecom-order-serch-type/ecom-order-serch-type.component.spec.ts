import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderSerchTypeComponent } from 'app/entities/ecom-order-serch-type/ecom-order-serch-type.component';
import { EcomOrderSerchTypeService } from 'app/entities/ecom-order-serch-type/ecom-order-serch-type.service';
import { EcomOrderSerchType } from 'app/shared/model/ecom-order-serch-type.model';

describe('Component Tests', () => {
  describe('EcomOrderSerchType Management Component', () => {
    let comp: EcomOrderSerchTypeComponent;
    let fixture: ComponentFixture<EcomOrderSerchTypeComponent>;
    let service: EcomOrderSerchTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderSerchTypeComponent],
      })
        .overrideTemplate(EcomOrderSerchTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomOrderSerchTypeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomOrderSerchTypeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomOrderSerchType(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomOrderSerchTypes && comp.ecomOrderSerchTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
