import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomMarkupSecondary } from 'app/shared/model/ecom-markup-secondary.model';

type EntityResponseType = HttpResponse<IEcomMarkupSecondary>;
type EntityArrayResponseType = HttpResponse<IEcomMarkupSecondary[]>;

@Injectable({ providedIn: 'root' })
export class EcomMarkupSecondaryService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-markup-secondaries';

  constructor(protected http: HttpClient) {}

  create(ecomMarkupSecondary: IEcomMarkupSecondary): Observable<EntityResponseType> {
    return this.http.post<IEcomMarkupSecondary>(this.resourceUrl, ecomMarkupSecondary, { observe: 'response' });
  }

  update(ecomMarkupSecondary: IEcomMarkupSecondary): Observable<EntityResponseType> {
    return this.http.put<IEcomMarkupSecondary>(this.resourceUrl, ecomMarkupSecondary, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomMarkupSecondary>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomMarkupSecondary[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
