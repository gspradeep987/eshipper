jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IShipmentService, ShipmentService } from '../shipment-service.model';
import { ShipmentServiceService } from '../service/shipment-service.service';

import { ShipmentServiceRoutingResolveService } from './shipment-service-routing-resolve.service';

describe('Service Tests', () => {
  describe('ShipmentService routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: ShipmentServiceRoutingResolveService;
    let service: ShipmentServiceService;
    let resultShipmentService: IShipmentService | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(ShipmentServiceRoutingResolveService);
      service = TestBed.inject(ShipmentServiceService);
      resultShipmentService = undefined;
    });

    describe('resolve', () => {
      it('should return IShipmentService returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultShipmentService = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultShipmentService).toEqual({ id: 123 });
      });

      it('should return new IShipmentService if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultShipmentService = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultShipmentService).toEqual(new ShipmentService());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultShipmentService = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultShipmentService).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
