import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomStoreColorTheme } from 'app/shared/model/ecom-store-color-theme.model';

type EntityResponseType = HttpResponse<IEcomStoreColorTheme>;
type EntityArrayResponseType = HttpResponse<IEcomStoreColorTheme[]>;

@Injectable({ providedIn: 'root' })
export class EcomStoreColorThemeService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-store-color-themes';

  constructor(protected http: HttpClient) {}

  create(ecomStoreColorTheme: IEcomStoreColorTheme): Observable<EntityResponseType> {
    return this.http.post<IEcomStoreColorTheme>(this.resourceUrl, ecomStoreColorTheme, { observe: 'response' });
  }

  update(ecomStoreColorTheme: IEcomStoreColorTheme): Observable<EntityResponseType> {
    return this.http.put<IEcomStoreColorTheme>(this.resourceUrl, ecomStoreColorTheme, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomStoreColorTheme>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomStoreColorTheme[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
