import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IShipmentService, ShipmentService } from '../shipment-service.model';
import { ShipmentServiceService } from '../service/shipment-service.service';

@Injectable({ providedIn: 'root' })
export class ShipmentServiceRoutingResolveService implements Resolve<IShipmentService> {
  constructor(protected service: ShipmentServiceService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IShipmentService> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((shipmentService: HttpResponse<ShipmentService>) => {
          if (shipmentService.body) {
            return of(shipmentService.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ShipmentService());
  }
}
