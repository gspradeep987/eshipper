jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IShippingAddress, ShippingAddress } from '../shipping-address.model';
import { ShippingAddressService } from '../service/shipping-address.service';

import { ShippingAddressRoutingResolveService } from './shipping-address-routing-resolve.service';

describe('Service Tests', () => {
  describe('ShippingAddress routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: ShippingAddressRoutingResolveService;
    let service: ShippingAddressService;
    let resultShippingAddress: IShippingAddress | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(ShippingAddressRoutingResolveService);
      service = TestBed.inject(ShippingAddressService);
      resultShippingAddress = undefined;
    });

    describe('resolve', () => {
      it('should return IShippingAddress returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultShippingAddress = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultShippingAddress).toEqual({ id: 123 });
      });

      it('should return new IShippingAddress if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultShippingAddress = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultShippingAddress).toEqual(new ShippingAddress());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultShippingAddress = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultShippingAddress).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
