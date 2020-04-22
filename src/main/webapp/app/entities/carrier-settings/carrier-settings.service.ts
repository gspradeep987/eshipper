import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICarrierSettings } from 'app/shared/model/carrier-settings.model';

type EntityResponseType = HttpResponse<ICarrierSettings>;
type EntityArrayResponseType = HttpResponse<ICarrierSettings[]>;

@Injectable({ providedIn: 'root' })
export class CarrierSettingsService {
  public resourceUrl = SERVER_API_URL + 'api/carrier-settings';

  constructor(protected http: HttpClient) {}

  create(carrierSettings: ICarrierSettings): Observable<EntityResponseType> {
    return this.http.post<ICarrierSettings>(this.resourceUrl, carrierSettings, { observe: 'response' });
  }

  update(carrierSettings: ICarrierSettings): Observable<EntityResponseType> {
    return this.http.put<ICarrierSettings>(this.resourceUrl, carrierSettings, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICarrierSettings>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICarrierSettings[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
