import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomOrder } from 'app/shared/model/ecom-order.model';

type EntityResponseType = HttpResponse<IEcomOrder>;
type EntityArrayResponseType = HttpResponse<IEcomOrder[]>;

@Injectable({ providedIn: 'root' })
export class EcomOrderService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-orders';

  constructor(protected http: HttpClient) {}

  create(ecomOrder: IEcomOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ecomOrder);
    return this.http
      .post<IEcomOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(ecomOrder: IEcomOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ecomOrder);
    return this.http
      .put<IEcomOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEcomOrder>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEcomOrder[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(ecomOrder: IEcomOrder): IEcomOrder {
    const copy: IEcomOrder = Object.assign({}, ecomOrder, {
      createdDate: ecomOrder.createdDate && ecomOrder.createdDate.isValid() ? ecomOrder.createdDate.format(DATE_FORMAT) : undefined,
      updatedDate: ecomOrder.updatedDate && ecomOrder.updatedDate.isValid() ? ecomOrder.updatedDate.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.updatedDate = res.body.updatedDate ? moment(res.body.updatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((ecomOrder: IEcomOrder) => {
        ecomOrder.createdDate = ecomOrder.createdDate ? moment(ecomOrder.createdDate) : undefined;
        ecomOrder.updatedDate = ecomOrder.updatedDate ? moment(ecomOrder.updatedDate) : undefined;
      });
    }
    return res;
  }
}
