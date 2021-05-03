jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IJobType, JobType } from '../job-type.model';
import { JobTypeService } from '../service/job-type.service';

import { JobTypeRoutingResolveService } from './job-type-routing-resolve.service';

describe('Service Tests', () => {
  describe('JobType routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: JobTypeRoutingResolveService;
    let service: JobTypeService;
    let resultJobType: IJobType | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(JobTypeRoutingResolveService);
      service = TestBed.inject(JobTypeService);
      resultJobType = undefined;
    });

    describe('resolve', () => {
      it('should return IJobType returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultJobType = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultJobType).toEqual({ id: 123 });
      });

      it('should return new IJobType if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultJobType = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultJobType).toEqual(new JobType());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultJobType = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultJobType).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
