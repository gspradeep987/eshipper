jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IEcomStoreMarkup, EcomStoreMarkup } from '../ecom-store-markup.model';
import { EcomStoreMarkupService } from '../service/ecom-store-markup.service';

import { EcomStoreMarkupRoutingResolveService } from './ecom-store-markup-routing-resolve.service';

describe('Service Tests', () => {
  describe('EcomStoreMarkup routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EcomStoreMarkupRoutingResolveService;
    let service: EcomStoreMarkupService;
    let resultEcomStoreMarkup: IEcomStoreMarkup | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EcomStoreMarkupRoutingResolveService);
      service = TestBed.inject(EcomStoreMarkupService);
      resultEcomStoreMarkup = undefined;
    });

    describe('resolve', () => {
      it('should return IEcomStoreMarkup returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStoreMarkup = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomStoreMarkup).toEqual({ id: 123 });
      });

      it('should return new IEcomStoreMarkup if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStoreMarkup = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEcomStoreMarkup).toEqual(new EcomStoreMarkup());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStoreMarkup = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomStoreMarkup).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
