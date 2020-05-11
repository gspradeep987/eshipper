import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data } from '@angular/router';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesOperationalDetailsComponent } from 'app/entities/wo-sales-operational-details/wo-sales-operational-details.component';
import { WoSalesOperationalDetailsService } from 'app/entities/wo-sales-operational-details/wo-sales-operational-details.service';
import { WoSalesOperationalDetails } from 'app/shared/model/wo-sales-operational-details.model';

describe('Component Tests', () => {
  describe('WoSalesOperationalDetails Management Component', () => {
    let comp: WoSalesOperationalDetailsComponent;
    let fixture: ComponentFixture<WoSalesOperationalDetailsComponent>;
    let service: WoSalesOperationalDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesOperationalDetailsComponent],
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
        .overrideTemplate(WoSalesOperationalDetailsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoSalesOperationalDetailsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoSalesOperationalDetailsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoSalesOperationalDetails(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woSalesOperationalDetails && comp.woSalesOperationalDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });

    it('should load a page', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WoSalesOperationalDetails(123)],
            headers
          })
        )
      );

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.woSalesOperationalDetails && comp.woSalesOperationalDetails[0]).toEqual(jasmine.objectContaining({ id: 123 }));
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
