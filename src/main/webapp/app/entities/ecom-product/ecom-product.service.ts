import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomProduct } from 'app/shared/model/ecom-product.model';

type EntityResponseType = HttpResponse<IEcomProduct>;
type EntityArrayResponseType = HttpResponse<IEcomProduct[]>;

@Injectable({ providedIn: 'root' })
export class EcomProductService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-products';

  constructor(protected http: HttpClient) {}

  create(ecomProduct: IEcomProduct): Observable<EntityResponseType> {
    return this.http.post<IEcomProduct>(this.resourceUrl, ecomProduct, { observe: 'response' });
  }

  update(ecomProduct: IEcomProduct): Observable<EntityResponseType> {
    return this.http.put<IEcomProduct>(this.resourceUrl, ecomProduct, { observe: 'response' });
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
}
