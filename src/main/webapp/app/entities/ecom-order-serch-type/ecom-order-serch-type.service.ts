import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomOrderSerchType } from 'app/shared/model/ecom-order-serch-type.model';

type EntityResponseType = HttpResponse<IEcomOrderSerchType>;
type EntityArrayResponseType = HttpResponse<IEcomOrderSerchType[]>;

@Injectable({ providedIn: 'root' })
export class EcomOrderSerchTypeService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-order-serch-types';

  constructor(protected http: HttpClient) {}

  create(ecomOrderSerchType: IEcomOrderSerchType): Observable<EntityResponseType> {
    return this.http.post<IEcomOrderSerchType>(this.resourceUrl, ecomOrderSerchType, { observe: 'response' });
  }

  update(ecomOrderSerchType: IEcomOrderSerchType): Observable<EntityResponseType> {
    return this.http.put<IEcomOrderSerchType>(this.resourceUrl, ecomOrderSerchType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomOrderSerchType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomOrderSerchType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
