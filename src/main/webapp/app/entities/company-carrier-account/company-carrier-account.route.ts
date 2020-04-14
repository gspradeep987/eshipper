import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICompanyCarrierAccount, CompanyCarrierAccount } from 'app/shared/model/company-carrier-account.model';
import { CompanyCarrierAccountService } from './company-carrier-account.service';
import { CompanyCarrierAccountComponent } from './company-carrier-account.component';
import { CompanyCarrierAccountDetailComponent } from './company-carrier-account-detail.component';
import { CompanyCarrierAccountUpdateComponent } from './company-carrier-account-update.component';

@Injectable({ providedIn: 'root' })
export class CompanyCarrierAccountResolve implements Resolve<ICompanyCarrierAccount> {
  constructor(private service: CompanyCarrierAccountService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICompanyCarrierAccount> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((companyCarrierAccount: HttpResponse<CompanyCarrierAccount>) => {
          if (companyCarrierAccount.body) {
            return of(companyCarrierAccount.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CompanyCarrierAccount());
  }
}

export const companyCarrierAccountRoute: Routes = [
  {
    path: '',
    component: CompanyCarrierAccountComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.companyCarrierAccount.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: CompanyCarrierAccountDetailComponent,
    resolve: {
      companyCarrierAccount: CompanyCarrierAccountResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.companyCarrierAccount.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: CompanyCarrierAccountUpdateComponent,
    resolve: {
      companyCarrierAccount: CompanyCarrierAccountResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.companyCarrierAccount.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: CompanyCarrierAccountUpdateComponent,
    resolve: {
      companyCarrierAccount: CompanyCarrierAccountResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.companyCarrierAccount.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
