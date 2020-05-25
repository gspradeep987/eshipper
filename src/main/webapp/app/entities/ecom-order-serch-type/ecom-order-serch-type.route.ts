import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomOrderSerchType, EcomOrderSerchType } from 'app/shared/model/ecom-order-serch-type.model';
import { EcomOrderSerchTypeService } from './ecom-order-serch-type.service';
import { EcomOrderSerchTypeComponent } from './ecom-order-serch-type.component';
import { EcomOrderSerchTypeDetailComponent } from './ecom-order-serch-type-detail.component';
import { EcomOrderSerchTypeUpdateComponent } from './ecom-order-serch-type-update.component';

@Injectable({ providedIn: 'root' })
export class EcomOrderSerchTypeResolve implements Resolve<IEcomOrderSerchType> {
  constructor(private service: EcomOrderSerchTypeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomOrderSerchType> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomOrderSerchType: HttpResponse<EcomOrderSerchType>) => {
          if (ecomOrderSerchType.body) {
            return of(ecomOrderSerchType.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomOrderSerchType());
  }
}

export const ecomOrderSerchTypeRoute: Routes = [
  {
    path: '',
    component: EcomOrderSerchTypeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomOrderSerchType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EcomOrderSerchTypeDetailComponent,
    resolve: {
      ecomOrderSerchType: EcomOrderSerchTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomOrderSerchType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EcomOrderSerchTypeUpdateComponent,
    resolve: {
      ecomOrderSerchType: EcomOrderSerchTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomOrderSerchType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EcomOrderSerchTypeUpdateComponent,
    resolve: {
      ecomOrderSerchType: EcomOrderSerchTypeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomOrderSerchType.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
