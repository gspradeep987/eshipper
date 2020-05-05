import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISignatureRequired, SignatureRequired } from 'app/shared/model/signature-required.model';
import { SignatureRequiredService } from './signature-required.service';
import { SignatureRequiredComponent } from './signature-required.component';
import { SignatureRequiredDetailComponent } from './signature-required-detail.component';
import { SignatureRequiredUpdateComponent } from './signature-required-update.component';

@Injectable({ providedIn: 'root' })
export class SignatureRequiredResolve implements Resolve<ISignatureRequired> {
  constructor(private service: SignatureRequiredService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISignatureRequired> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((signatureRequired: HttpResponse<SignatureRequired>) => {
          if (signatureRequired.body) {
            return of(signatureRequired.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SignatureRequired());
  }
}

export const signatureRequiredRoute: Routes = [
  {
    path: '',
    component: SignatureRequiredComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.signatureRequired.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SignatureRequiredDetailComponent,
    resolve: {
      signatureRequired: SignatureRequiredResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.signatureRequired.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SignatureRequiredUpdateComponent,
    resolve: {
      signatureRequired: SignatureRequiredResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.signatureRequired.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SignatureRequiredUpdateComponent,
    resolve: {
      signatureRequired: SignatureRequiredResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.signatureRequired.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
