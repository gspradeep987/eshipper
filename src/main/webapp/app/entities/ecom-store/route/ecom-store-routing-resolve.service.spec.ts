jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IEcomStore, EcomStore } from '../ecom-store.model';
import { EcomStoreService } from '../service/ecom-store.service';

import { EcomStoreRoutingResolveService } from './ecom-store-routing-resolve.service';

describe('Service Tests', () => {
  describe('EcomStore routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EcomStoreRoutingResolveService;
    let service: EcomStoreService;
    let resultEcomStore: IEcomStore | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EcomStoreRoutingResolveService);
      service = TestBed.inject(EcomStoreService);
      resultEcomStore = undefined;
    });

    describe('resolve', () => {
      it('should return IEcomStore returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStore = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomStore).toEqual({ id: 123 });
      });

      it('should return new IEcomStore if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStore = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEcomStore).toEqual(new EcomStore());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStore = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomStore).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
