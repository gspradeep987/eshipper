import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomMarkupTertiary } from 'app/shared/model/ecom-markup-tertiary.model';

type EntityResponseType = HttpResponse<IEcomMarkupTertiary>;
type EntityArrayResponseType = HttpResponse<IEcomMarkupTertiary[]>;

@Injectable({ providedIn: 'root' })
export class EcomMarkupTertiaryService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-markup-tertiaries';

  constructor(protected http: HttpClient) {}

  create(ecomMarkupTertiary: IEcomMarkupTertiary): Observable<EntityResponseType> {
    return this.http.post<IEcomMarkupTertiary>(this.resourceUrl, ecomMarkupTertiary, { observe: 'response' });
  }

  update(ecomMarkupTertiary: IEcomMarkupTertiary): Observable<EntityResponseType> {
    return this.http.put<IEcomMarkupTertiary>(this.resourceUrl, ecomMarkupTertiary, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomMarkupTertiary>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomMarkupTertiary[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
