jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IEcomStorePackageSettings, EcomStorePackageSettings } from '../ecom-store-package-settings.model';
import { EcomStorePackageSettingsService } from '../service/ecom-store-package-settings.service';

import { EcomStorePackageSettingsRoutingResolveService } from './ecom-store-package-settings-routing-resolve.service';

describe('Service Tests', () => {
  describe('EcomStorePackageSettings routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EcomStorePackageSettingsRoutingResolveService;
    let service: EcomStorePackageSettingsService;
    let resultEcomStorePackageSettings: IEcomStorePackageSettings | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EcomStorePackageSettingsRoutingResolveService);
      service = TestBed.inject(EcomStorePackageSettingsService);
      resultEcomStorePackageSettings = undefined;
    });

    describe('resolve', () => {
      it('should return IEcomStorePackageSettings returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStorePackageSettings = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomStorePackageSettings).toEqual({ id: 123 });
      });

      it('should return new IEcomStorePackageSettings if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStorePackageSettings = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEcomStorePackageSettings).toEqual(new EcomStorePackageSettings());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStorePackageSettings = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomStorePackageSettings).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
