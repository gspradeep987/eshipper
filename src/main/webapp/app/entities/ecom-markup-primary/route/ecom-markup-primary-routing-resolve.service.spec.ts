jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IEcomMarkupPrimary, EcomMarkupPrimary } from '../ecom-markup-primary.model';
import { EcomMarkupPrimaryService } from '../service/ecom-markup-primary.service';

import { EcomMarkupPrimaryRoutingResolveService } from './ecom-markup-primary-routing-resolve.service';

describe('Service Tests', () => {
  describe('EcomMarkupPrimary routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EcomMarkupPrimaryRoutingResolveService;
    let service: EcomMarkupPrimaryService;
    let resultEcomMarkupPrimary: IEcomMarkupPrimary | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EcomMarkupPrimaryRoutingResolveService);
      service = TestBed.inject(EcomMarkupPrimaryService);
      resultEcomMarkupPrimary = undefined;
    });

    describe('resolve', () => {
      it('should return IEcomMarkupPrimary returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomMarkupPrimary = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomMarkupPrimary).toEqual({ id: 123 });
      });

      it('should return new IEcomMarkupPrimary if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomMarkupPrimary = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEcomMarkupPrimary).toEqual(new EcomMarkupPrimary());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomMarkupPrimary = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomMarkupPrimary).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
