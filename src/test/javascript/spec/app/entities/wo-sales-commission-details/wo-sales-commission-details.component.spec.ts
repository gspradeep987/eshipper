import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesCommissionDetailsComponent } from 'app/entities/wo-sales-commission-details/wo-sales-commission-details.component';
import { WoSalesCommissionDetailsService } from 'app/entities/wo-sales-commission-details/wo-sales-commission-details.service';
import { WoSalesCommissionDetails } from 'app/shared/model/wo-sales-commission-details.model';

describe('Component Tests', () => {
  describe('WoSalesCommissionDetails Management Component', () => {
    let comp: WoSalesCommissionDetailsComponent;
    let fixture: ComponentFixture<WoSalesCommissionDetailsComponent>;
    let service: WoSalesCommissionDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesCommissionDetailsComponent],
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
        .overrideTemplate(WoSalesCommissionDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoSalesCommissionDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoSalesCommissionDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoSalesCommissionDetails(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woSalesCommissionDetails && comp.woSalesCommissionDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoSalesCommissionDetails(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woSalesCommissionDetails && comp.woSalesCommissionDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
