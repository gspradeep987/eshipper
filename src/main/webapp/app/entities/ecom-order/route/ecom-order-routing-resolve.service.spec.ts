jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IEcomOrder, EcomOrder } from '../ecom-order.model';
import { EcomOrderService } from '../service/ecom-order.service';

import { EcomOrderRoutingResolveService } from './ecom-order-routing-resolve.service';

describe('Service Tests', () => {
  describe('EcomOrder routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EcomOrderRoutingResolveService;
    let service: EcomOrderService;
    let resultEcomOrder: IEcomOrder | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EcomOrderRoutingResolveService);
      service = TestBed.inject(EcomOrderService);
      resultEcomOrder = undefined;
    });

    describe('resolve', () => {
      it('should return IEcomOrder returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomOrder = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomOrder).toEqual({ id: 123 });
      });

      it('should return new IEcomOrder if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomOrder = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEcomOrder).toEqual(new EcomOrder());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomOrder = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomOrder).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
