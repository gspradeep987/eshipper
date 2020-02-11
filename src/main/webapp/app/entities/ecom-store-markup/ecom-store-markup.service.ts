import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomStoreMarkup } from 'app/shared/model/ecom-store-markup.model';

type EntityResponseType = HttpResponse<IEcomStoreMarkup>;
type EntityArrayResponseType = HttpResponse<IEcomStoreMarkup[]>;

@Injectable({ providedIn: 'root' })
export class EcomStoreMarkupService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-store-markups';

  constructor(protected http: HttpClient) {}

  create(ecomStoreMarkup: IEcomStoreMarkup): Observable<EntityResponseType> {
    return this.http.post<IEcomStoreMarkup>(this.resourceUrl, ecomStoreMarkup, { observe: 'response' });
  }

  update(ecomStoreMarkup: IEcomStoreMarkup): Observable<EntityResponseType> {
    return this.http.put<IEcomStoreMarkup>(this.resourceUrl, ecomStoreMarkup, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomStoreMarkup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomStoreMarkup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
