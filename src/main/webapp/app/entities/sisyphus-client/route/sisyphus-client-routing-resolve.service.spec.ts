jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ISisyphusClient, SisyphusClient } from '../sisyphus-client.model';
import { SisyphusClientService } from '../service/sisyphus-client.service';

import { SisyphusClientRoutingResolveService } from './sisyphus-client-routing-resolve.service';

describe('Service Tests', () => {
  describe('SisyphusClient routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: SisyphusClientRoutingResolveService;
    let service: SisyphusClientService;
    let resultSisyphusClient: ISisyphusClient | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(SisyphusClientRoutingResolveService);
      service = TestBed.inject(SisyphusClientService);
      resultSisyphusClient = undefined;
    });

    describe('resolve', () => {
      it('should return ISisyphusClient returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusClient = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultSisyphusClient).toEqual({ id: 123 });
      });

      it('should return new ISisyphusClient if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusClient = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultSisyphusClient).toEqual(new SisyphusClient());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusClient = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultSisyphusClient).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
