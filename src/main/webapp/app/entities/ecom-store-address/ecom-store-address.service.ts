import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomStoreAddress } from 'app/shared/model/ecom-store-address.model';

type EntityResponseType = HttpResponse<IEcomStoreAddress>;
type EntityArrayResponseType = HttpResponse<IEcomStoreAddress[]>;

@Injectable({ providedIn: 'root' })
export class EcomStoreAddressService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-store-addresses';

  constructor(protected http: HttpClient) {}

  create(ecomStoreAddress: IEcomStoreAddress): Observable<EntityResponseType> {
    return this.http.post<IEcomStoreAddress>(this.resourceUrl, ecomStoreAddress, { observe: 'response' });
  }

  update(ecomStoreAddress: IEcomStoreAddress): Observable<EntityResponseType> {
    return this.http.put<IEcomStoreAddress>(this.resourceUrl, ecomStoreAddress, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomStoreAddress>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomStoreAddress[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
