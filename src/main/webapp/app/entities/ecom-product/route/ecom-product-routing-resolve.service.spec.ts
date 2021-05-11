jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IEcomProduct, EcomProduct } from '../ecom-product.model';
import { EcomProductService } from '../service/ecom-product.service';

import { EcomProductRoutingResolveService } from './ecom-product-routing-resolve.service';

describe('Service Tests', () => {
  describe('EcomProduct routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EcomProductRoutingResolveService;
    let service: EcomProductService;
    let resultEcomProduct: IEcomProduct | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EcomProductRoutingResolveService);
      service = TestBed.inject(EcomProductService);
      resultEcomProduct = undefined;
    });

    describe('resolve', () => {
      it('should return IEcomProduct returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomProduct = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomProduct).toEqual({ id: 123 });
      });

      it('should return new IEcomProduct if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomProduct = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEcomProduct).toEqual(new EcomProduct());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomProduct = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomProduct).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
