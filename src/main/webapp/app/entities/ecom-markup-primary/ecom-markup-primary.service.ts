import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomMarkupPrimary } from 'app/shared/model/ecom-markup-primary.model';

type EntityResponseType = HttpResponse<IEcomMarkupPrimary>;
type EntityArrayResponseType = HttpResponse<IEcomMarkupPrimary[]>;

@Injectable({ providedIn: 'root' })
export class EcomMarkupPrimaryService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-markup-primaries';

  constructor(protected http: HttpClient) {}

  create(ecomMarkupPrimary: IEcomMarkupPrimary): Observable<EntityResponseType> {
    return this.http.post<IEcomMarkupPrimary>(this.resourceUrl, ecomMarkupPrimary, { observe: 'response' });
  }

  update(ecomMarkupPrimary: IEcomMarkupPrimary): Observable<EntityResponseType> {
    return this.http.put<IEcomMarkupPrimary>(this.resourceUrl, ecomMarkupPrimary, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomMarkupPrimary>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomMarkupPrimary[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
