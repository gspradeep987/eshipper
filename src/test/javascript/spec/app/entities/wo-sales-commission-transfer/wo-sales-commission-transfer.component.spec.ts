import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, convertToParamMap, Data } from '@angular/router';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesCommissionTransferComponent } from 'app/entities/wo-sales-commission-transfer/wo-sales-commission-transfer.component';
import { WoSalesCommissionTransferService } from 'app/entities/wo-sales-commission-transfer/wo-sales-commission-transfer.service';
import { WoSalesCommissionTransfer } from 'app/shared/model/wo-sales-commission-transfer.model';

describe('Component Tests', () => {
  describe('WoSalesCommissionTransfer Management Component', () => {
    let comp: WoSalesCommissionTransferComponent;
    let fixture: ComponentFixture<WoSalesCommissionTransferComponent>;
    let service: WoSalesCommissionTransferService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesCommissionTransferComponent],
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
                      page: 0,
                    },
                  }),
              },
              queryParamMap: {
                subscribe: (fn: (value: Data) => void) =>
                  fn(
                    convertToParamMap({
                      page: '1',
                      size: '1',
                      sort: 'id,desc',
                    })
                  ),
              },
            },
          },
        ],
      })
        .overrideTemplate(WoSalesCommissionTransferComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoSalesCommissionTransferComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoSalesCommissionTransferService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoSalesCommissionTransfer(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woSalesCommissionTransfers && comp.woSalesCommissionTransfers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoSalesCommissionTransfer(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woSalesCommissionTransfers && comp.woSalesCommissionTransfers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
