import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomMarkupQuaternary } from 'app/shared/model/ecom-markup-quaternary.model';

type EntityResponseType = HttpResponse<IEcomMarkupQuaternary>;
type EntityArrayResponseType = HttpResponse<IEcomMarkupQuaternary[]>;

@Injectable({ providedIn: 'root' })
export class EcomMarkupQuaternaryService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-markup-quaternaries';

  constructor(protected http: HttpClient) {}

  create(ecomMarkupQuaternary: IEcomMarkupQuaternary): Observable<EntityResponseType> {
    return this.http.post<IEcomMarkupQuaternary>(this.resourceUrl, ecomMarkupQuaternary, { observe: 'response' });
  }

  update(ecomMarkupQuaternary: IEcomMarkupQuaternary): Observable<EntityResponseType> {
    return this.http.put<IEcomMarkupQuaternary>(this.resourceUrl, ecomMarkupQuaternary, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomMarkupQuaternary>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomMarkupQuaternary[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
