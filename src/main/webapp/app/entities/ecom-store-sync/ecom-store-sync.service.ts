import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomStoreSync } from 'app/shared/model/ecom-store-sync.model';

type EntityResponseType = HttpResponse<IEcomStoreSync>;
type EntityArrayResponseType = HttpResponse<IEcomStoreSync[]>;

@Injectable({ providedIn: 'root' })
export class EcomStoreSyncService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-store-syncs';

  constructor(protected http: HttpClient) {}

  create(ecomStoreSync: IEcomStoreSync): Observable<EntityResponseType> {
    return this.http.post<IEcomStoreSync>(this.resourceUrl, ecomStoreSync, { observe: 'response' });
  }

  update(ecomStoreSync: IEcomStoreSync): Observable<EntityResponseType> {
    return this.http.put<IEcomStoreSync>(this.resourceUrl, ecomStoreSync, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomStoreSync>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomStoreSync[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
