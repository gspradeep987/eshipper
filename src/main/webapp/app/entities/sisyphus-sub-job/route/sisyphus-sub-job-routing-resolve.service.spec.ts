jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ISisyphusSubJob, SisyphusSubJob } from '../sisyphus-sub-job.model';
import { SisyphusSubJobService } from '../service/sisyphus-sub-job.service';

import { SisyphusSubJobRoutingResolveService } from './sisyphus-sub-job-routing-resolve.service';

describe('Service Tests', () => {
  describe('SisyphusSubJob routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: SisyphusSubJobRoutingResolveService;
    let service: SisyphusSubJobService;
    let resultSisyphusSubJob: ISisyphusSubJob | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(SisyphusSubJobRoutingResolveService);
      service = TestBed.inject(SisyphusSubJobService);
      resultSisyphusSubJob = undefined;
    });

    describe('resolve', () => {
      it('should return ISisyphusSubJob returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusSubJob = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultSisyphusSubJob).toEqual({ id: 123 });
      });

      it('should return new ISisyphusSubJob if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusSubJob = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultSisyphusSubJob).toEqual(new SisyphusSubJob());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusSubJob = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultSisyphusSubJob).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
