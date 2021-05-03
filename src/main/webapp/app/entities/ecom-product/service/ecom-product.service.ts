import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomProduct, getEcomProductIdentifier } from '../ecom-product.model';

export type EntityResponseType = HttpResponse<IEcomProduct>;
export type EntityArrayResponseType = HttpResponse<IEcomProduct[]>;

@Injectable({ providedIn: 'root' })
export class EcomProductService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-products');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomProduct: IEcomProduct): Observable<EntityResponseType> {
    return this.http.post<IEcomProduct>(this.resourceUrl, ecomProduct, { observe: 'response' });
  }

  update(ecomProduct: IEcomProduct): Observable<EntityResponseType> {
    return this.http.put<IEcomProduct>(`${this.resourceUrl}/${getEcomProductIdentifier(ecomProduct) as number}`, ecomProduct, {
      observe: 'response',
    });
  }

  partialUpdate(ecomProduct: IEcomProduct): Observable<EntityResponseType> {
    return this.http.patch<IEcomProduct>(`${this.resourceUrl}/${getEcomProductIdentifier(ecomProduct) as number}`, ecomProduct, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomProduct>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomProduct[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEcomProductToCollectionIfMissing(
    ecomProductCollection: IEcomProduct[],
    ...ecomProductsToCheck: (IEcomProduct | null | undefined)[]
  ): IEcomProduct[] {
    const ecomProducts: IEcomProduct[] = ecomProductsToCheck.filter(isPresent);
    if (ecomProducts.length > 0) {
      const ecomProductCollectionIdentifiers = ecomProductCollection.map(ecomProductItem => getEcomProductIdentifier(ecomProductItem)!);
      const ecomProductsToAdd = ecomProducts.filter(ecomProductItem => {
        const ecomProductIdentifier = getEcomProductIdentifier(ecomProductItem);
        if (ecomProductIdentifier == null || ecomProductCollectionIdentifiers.includes(ecomProductIdentifier)) {
          return false;
        }
        ecomProductCollectionIdentifiers.push(ecomProductIdentifier);
        return true;
      });
      return [...ecomProductsToAdd, ...ecomProductCollection];
    }
    return ecomProductCollection;
  }
}
