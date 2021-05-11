jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ISisyphusJobType, SisyphusJobType } from '../sisyphus-job-type.model';
import { SisyphusJobTypeService } from '../service/sisyphus-job-type.service';

import { SisyphusJobTypeRoutingResolveService } from './sisyphus-job-type-routing-resolve.service';

describe('Service Tests', () => {
  describe('SisyphusJobType routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: SisyphusJobTypeRoutingResolveService;
    let service: SisyphusJobTypeService;
    let resultSisyphusJobType: ISisyphusJobType | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(SisyphusJobTypeRoutingResolveService);
      service = TestBed.inject(SisyphusJobTypeService);
      resultSisyphusJobType = undefined;
    });

    describe('resolve', () => {
      it('should return ISisyphusJobType returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusJobType = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultSisyphusJobType).toEqual({ id: 123 });
      });

      it('should return new ISisyphusJobType if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusJobType = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultSisyphusJobType).toEqual(new SisyphusJobType());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusJobType = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultSisyphusJobType).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
