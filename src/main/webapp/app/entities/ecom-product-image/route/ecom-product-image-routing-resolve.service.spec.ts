jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IEcomProductImage, EcomProductImage } from '../ecom-product-image.model';
import { EcomProductImageService } from '../service/ecom-product-image.service';

import { EcomProductImageRoutingResolveService } from './ecom-product-image-routing-resolve.service';

describe('Service Tests', () => {
  describe('EcomProductImage routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EcomProductImageRoutingResolveService;
    let service: EcomProductImageService;
    let resultEcomProductImage: IEcomProductImage | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EcomProductImageRoutingResolveService);
      service = TestBed.inject(EcomProductImageService);
      resultEcomProductImage = undefined;
    });

    describe('resolve', () => {
      it('should return IEcomProductImage returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomProductImage = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomProductImage).toEqual({ id: 123 });
      });

      it('should return new IEcomProductImage if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomProductImage = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEcomProductImage).toEqual(new EcomProductImage());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomProductImage = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomProductImage).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
