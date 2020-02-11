import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomWarehouse, EcomWarehouse } from 'app/shared/model/ecom-warehouse.model';
import { EcomWarehouseService } from './ecom-warehouse.service';
import { EcomWarehouseComponent } from './ecom-warehouse.component';
import { EcomWarehouseDetailComponent } from './ecom-warehouse-detail.component';
import { EcomWarehouseUpdateComponent } from './ecom-warehouse-update.component';

@Injectable({ providedIn: 'root' })
export class EcomWarehouseResolve implements Resolve<IEcomWarehouse> {
  constructor(private service: EcomWarehouseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomWarehouse> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomWarehouse: HttpResponse<EcomWarehouse>) => {
          if (ecomWarehouse.body) {
            return of(ecomWarehouse.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomWarehouse());
  }
}

export const ecomWarehouseRoute: Routes = [
  {
    path: '',
    component: EcomWarehouseComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomWarehouse.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomWarehouseDetailComponent,
    resolve: {
      ecomWarehouse: EcomWarehouseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomWarehouse.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomWarehouseUpdateComponent,
    resolve: {
      ecomWarehouse: EcomWarehouseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomWarehouse.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomWarehouseUpdateComponent,
    resolve: {
      ecomWarehouse: EcomWarehouseResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'eshipperApp.ecomWarehouse.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
