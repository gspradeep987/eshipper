import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IEcomProductImage, EcomProductImage } from '../ecom-product-image.model';
import { EcomProductImageService } from '../service/ecom-product-image.service';

@Injectable({ providedIn: 'root' })
export class EcomProductImageRoutingResolveService implements Resolve<IEcomProductImage> {
  constructor(protected service: EcomProductImageService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEcomProductImage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((ecomProductImage: HttpResponse<EcomProductImage>) => {
          if (ecomProductImage.body) {
            return of(ecomProductImage.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EcomProductImage());
  }
}
