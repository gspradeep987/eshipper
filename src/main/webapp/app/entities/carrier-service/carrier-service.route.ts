import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICarrierService, CarrierService } from 'app/shared/model/carrier-service.model';
import { CarrierServiceService } from './carrier-service.service';
import { CarrierServiceComponent } from './carrier-service.component';
import { CarrierServiceDetailComponent } from './carrier-service-detail.component';
import { CarrierServiceUpdateComponent } from './carrier-service-update.component';

@Injectable({ providedIn: 'root' })
export class CarrierServiceResolve implements Resolve<ICarrierService> {
  constructor(private service: CarrierServiceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICarrierService> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((carrierService: HttpResponse<CarrierService>) => {
          if (carrierService.body) {
            return of(carrierService.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CarrierService());
  }
}

export const carrierServiceRoute: Routes = [
  {
    path: '',
    component: CarrierServiceComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams,
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'eshipperApp.carrierService.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CarrierServiceDetailComponent,
    resolve: {
      carrierService: CarrierServiceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.carrierService.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CarrierServiceUpdateComponent,
    resolve: {
      carrierService: CarrierServiceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.carrierService.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CarrierServiceUpdateComponent,
    resolve: {
      carrierService: CarrierServiceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.carrierService.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
