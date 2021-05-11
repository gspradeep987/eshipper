jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ISisyphusJobParam, SisyphusJobParam } from '../sisyphus-job-param.model';
import { SisyphusJobParamService } from '../service/sisyphus-job-param.service';

import { SisyphusJobParamRoutingResolveService } from './sisyphus-job-param-routing-resolve.service';

describe('Service Tests', () => {
  describe('SisyphusJobParam routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: SisyphusJobParamRoutingResolveService;
    let service: SisyphusJobParamService;
    let resultSisyphusJobParam: ISisyphusJobParam | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(SisyphusJobParamRoutingResolveService);
      service = TestBed.inject(SisyphusJobParamService);
      resultSisyphusJobParam = undefined;
    });

    describe('resolve', () => {
      it('should return ISisyphusJobParam returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusJobParam = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultSisyphusJobParam).toEqual({ id: 123 });
      });

      it('should return new ISisyphusJobParam if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusJobParam = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultSisyphusJobParam).toEqual(new SisyphusJobParam());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusJobParam = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultSisyphusJobParam).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
