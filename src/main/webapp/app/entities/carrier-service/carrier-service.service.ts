import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICarrierService } from 'app/shared/model/carrier-service.model';

type EntityResponseType = HttpResponse<ICarrierService>;
type EntityArrayResponseType = HttpResponse<ICarrierService[]>;

@Injectable({ providedIn: 'root' })
export class CarrierServiceService {
  public resourceUrl = SERVER_API_URL + 'api/carrier-services';

  constructor(protected http: HttpClient) {}

  create(carrierService: ICarrierService): Observable<EntityResponseType> {
    return this.http.post<ICarrierService>(this.resourceUrl, carrierService, { observe: 'response' });
  }

  update(carrierService: ICarrierService): Observable<EntityResponseType> {
    return this.http.put<ICarrierService>(this.resourceUrl, carrierService, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICarrierService>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICarrierService[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
