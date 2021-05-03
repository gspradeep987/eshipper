jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IEcomStoreShipmentSettings, EcomStoreShipmentSettings } from '../ecom-store-shipment-settings.model';
import { EcomStoreShipmentSettingsService } from '../service/ecom-store-shipment-settings.service';

import { EcomStoreShipmentSettingsRoutingResolveService } from './ecom-store-shipment-settings-routing-resolve.service';

describe('Service Tests', () => {
  describe('EcomStoreShipmentSettings routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: EcomStoreShipmentSettingsRoutingResolveService;
    let service: EcomStoreShipmentSettingsService;
    let resultEcomStoreShipmentSettings: IEcomStoreShipmentSettings | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(EcomStoreShipmentSettingsRoutingResolveService);
      service = TestBed.inject(EcomStoreShipmentSettingsService);
      resultEcomStoreShipmentSettings = undefined;
    });

    describe('resolve', () => {
      it('should return IEcomStoreShipmentSettings returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStoreShipmentSettings = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomStoreShipmentSettings).toEqual({ id: 123 });
      });

      it('should return new IEcomStoreShipmentSettings if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStoreShipmentSettings = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultEcomStoreShipmentSettings).toEqual(new EcomStoreShipmentSettings());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultEcomStoreShipmentSettings = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultEcomStoreShipmentSettings).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
