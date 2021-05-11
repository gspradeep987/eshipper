jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { ISisyphusJob, SisyphusJob } from '../sisyphus-job.model';
import { SisyphusJobService } from '../service/sisyphus-job.service';

import { SisyphusJobRoutingResolveService } from './sisyphus-job-routing-resolve.service';

describe('Service Tests', () => {
  describe('SisyphusJob routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: SisyphusJobRoutingResolveService;
    let service: SisyphusJobService;
    let resultSisyphusJob: ISisyphusJob | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(SisyphusJobRoutingResolveService);
      service = TestBed.inject(SisyphusJobService);
      resultSisyphusJob = undefined;
    });

    describe('resolve', () => {
      it('should return ISisyphusJob returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusJob = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultSisyphusJob).toEqual({ id: 123 });
      });

      it('should return new ISisyphusJob if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusJob = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultSisyphusJob).toEqual(new SisyphusJob());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultSisyphusJob = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultSisyphusJob).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
