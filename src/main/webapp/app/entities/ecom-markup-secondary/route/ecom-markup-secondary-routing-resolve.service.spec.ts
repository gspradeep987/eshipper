jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IEcomMarkupSecondary, EcomMarkupSecondary } from '../ecom-markup-secondary.model';
import { EcomMarkupSecondaryService } from '../service/ecom-markup-secondary.service';

import { EcomMarkupSecondaryRoutingResolveService } from './ecom-markup-secondary-routing-resolve.service';

describe('Service Tests', () => {
  describe('EcomMarkupSecondary routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EcomMarkupSecondaryRoutingResolveService;
    let service: EcomMarkupSecondaryService;
    let resultEcomMarkupSecondary: IEcomMarkupSecondary | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EcomMarkupSecondaryRoutingResolveService);
      service = TestBed.inject(EcomMarkupSecondaryService);
      resultEcomMarkupSecondary = undefined;
    });

    describe('resolve', () => {
      it('should return IEcomMarkupSecondary returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomMarkupSecondary = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomMarkupSecondary).toEqual({ id: 123 });
      });

      it('should return new IEcomMarkupSecondary if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomMarkupSecondary = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEcomMarkupSecondary).toEqual(new EcomMarkupSecondary());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomMarkupSecondary = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomMarkupSecondary).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
