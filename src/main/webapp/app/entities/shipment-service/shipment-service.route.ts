import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IShipmentService, ShipmentService } from 'app/shared/model/shipment-service.model';
import { ShipmentServiceService } from './shipment-service.service';
import { ShipmentServiceComponent } from './shipment-service.component';
import { ShipmentServiceDetailComponent } from './shipment-service-detail.component';
import { ShipmentServiceUpdateComponent } from './shipment-service-update.component';

@Injectable({ providedIn: 'root' })
export class ShipmentServiceResolve implements Resolve<IShipmentService> {
  constructor(private service: ShipmentServiceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IShipmentService> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((shipmentService: HttpResponse<ShipmentService>) => {
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

export const shipmentServiceRoute: Routes = [
  {
    path: '',
    component: ShipmentServiceComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shipmentService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ShipmentServiceDetailComponent,
    resolve: {
      shipmentService: ShipmentServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shipmentService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ShipmentServiceUpdateComponent,
    resolve: {
      shipmentService: ShipmentServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shipmentService.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ShipmentServiceUpdateComponent,
    resolve: {
      shipmentService: ShipmentServiceResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.shipmentService.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
