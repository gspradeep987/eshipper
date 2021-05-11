jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IEcomStoreAddress, EcomStoreAddress } from '../ecom-store-address.model';
import { EcomStoreAddressService } from '../service/ecom-store-address.service';

import { EcomStoreAddressRoutingResolveService } from './ecom-store-address-routing-resolve.service';

describe('Service Tests', () => {
  describe('EcomStoreAddress routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EcomStoreAddressRoutingResolveService;
    let service: EcomStoreAddressService;
    let resultEcomStoreAddress: IEcomStoreAddress | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EcomStoreAddressRoutingResolveService);
      service = TestBed.inject(EcomStoreAddressService);
      resultEcomStoreAddress = undefined;
    });

    describe('resolve', () => {
      it('should return IEcomStoreAddress returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStoreAddress = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomStoreAddress).toEqual({ id: 123 });
      });

      it('should return new IEcomStoreAddress if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStoreAddress = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEcomStoreAddress).toEqual(new EcomStoreAddress());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStoreAddress = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomStoreAddress).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
