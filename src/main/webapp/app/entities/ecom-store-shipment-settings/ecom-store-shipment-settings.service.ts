import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomStoreShipmentSettings } from 'app/shared/model/ecom-store-shipment-settings.model';

type EntityResponseType = HttpResponse<IEcomStoreShipmentSettings>;
type EntityArrayResponseType = HttpResponse<IEcomStoreShipmentSettings[]>;

@Injectable({ providedIn: 'root' })
export class EcomStoreShipmentSettingsService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-store-shipment-settings';

  constructor(protected http: HttpClient) {}

  create(ecomStoreShipmentSettings: IEcomStoreShipmentSettings): Observable<EntityResponseType> {
    return this.http.post<IEcomStoreShipmentSettings>(this.resourceUrl, ecomStoreShipmentSettings, { observe: 'response' });
  }

  update(ecomStoreShipmentSettings: IEcomStoreShipmentSettings): Observable<EntityResponseType> {
    return this.http.put<IEcomStoreShipmentSettings>(this.resourceUrl, ecomStoreShipmentSettings, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomStoreShipmentSettings>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomStoreShipmentSettings[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
