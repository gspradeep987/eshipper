import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEcomorderAttachment, EcomorderAttachment } from 'app/shared/model/ecomorder-attachment.model';
import { EcomorderAttachmentService } from './ecomorder-attachment.service';
import { EcomorderAttachmentComponent } from './ecomorder-attachment.component';
import { EcomorderAttachmentDetailComponent } from './ecomorder-attachment-detail.component';
import { EcomorderAttachmentUpdateComponent } from './ecomorder-attachment-update.component';

@Injectable({ providedIn: 'root' })
export class EcomorderAttachmentResolve implements Resolve<IEcomorderAttachment> {
  constructor(private service: EcomorderAttachmentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomorderAttachment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ecomorderAttachment: HttpResponse<EcomorderAttachment>) => {
          if (ecomorderAttachment.body) {
            return of(ecomorderAttachment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomorderAttachment());
  }
}

export const ecomorderAttachmentRoute: Routes = [
  {
    path: '',
    component: EcomorderAttachmentComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomorderAttachment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: EcomorderAttachmentDetailComponent,
    resolve: {
      ecomorderAttachment: EcomorderAttachmentResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomorderAttachment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: EcomorderAttachmentUpdateComponent,
    resolve: {
      ecomorderAttachment: EcomorderAttachmentResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomorderAttachment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: EcomorderAttachmentUpdateComponent,
    resolve: {
      ecomorderAttachment: EcomorderAttachmentResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'eshipperApp.ecomorderAttachment.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
