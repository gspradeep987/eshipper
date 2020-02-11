import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomStorePackageSettings } from 'app/shared/model/ecom-store-package-settings.model';

type EntityResponseType = HttpResponse<IEcomStorePackageSettings>;
type EntityArrayResponseType = HttpResponse<IEcomStorePackageSettings[]>;

@Injectable({ providedIn: 'root' })
export class EcomStorePackageSettingsService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-store-package-settings';

  constructor(protected http: HttpClient) {}

  create(ecomStorePackageSettings: IEcomStorePackageSettings): Observable<EntityResponseType> {
    return this.http.post<IEcomStorePackageSettings>(this.resourceUrl, ecomStorePackageSettings, { observe: 'response' });
  }

  update(ecomStorePackageSettings: IEcomStorePackageSettings): Observable<EntityResponseType> {
    return this.http.put<IEcomStorePackageSettings>(this.resourceUrl, ecomStorePackageSettings, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomStorePackageSettings>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomStorePackageSettings[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
