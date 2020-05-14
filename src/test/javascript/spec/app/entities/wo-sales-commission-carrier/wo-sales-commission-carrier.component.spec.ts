import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesCommissionCarrierComponent } from 'app/entities/wo-sales-commission-carrier/wo-sales-commission-carrier.component';
import { WoSalesCommissionCarrierService } from 'app/entities/wo-sales-commission-carrier/wo-sales-commission-carrier.service';
import { WoSalesCommissionCarrier } from 'app/shared/model/wo-sales-commission-carrier.model';

describe('Component Tests', () => {
  describe('WoSalesCommissionCarrier Management Component', () => {
    let comp: WoSalesCommissionCarrierComponent;
    let fixture: ComponentFixture<WoSalesCommissionCarrierComponent>;
    let service: WoSalesCommissionCarrierService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesCommissionCarrierComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: {
              data: {
                subscribe: (fn: (value: Data) => void) =>
                  fn({
                    pagingParams: {
                      predicate: 'id',
                      reverse: false,
                      page: 0
                    }
                  })
              }
            }
          }
        ]
      })
        .overrideTemplate(WoSalesCommissionCarrierComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoSalesCommissionCarrierComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoSalesCommissionCarrierService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoSalesCommissionCarrier(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woSalesCommissionCarriers && comp.woSalesCommissionCarriers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoSalesCommissionCarrier(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woSalesCommissionCarriers && comp.woSalesCommissionCarriers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      comp.ngOnInit();
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,desc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // INIT
      comp.ngOnInit();

      // GIVEN
      comp.predicate = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,desc', 'id']);
    });
  });
});
