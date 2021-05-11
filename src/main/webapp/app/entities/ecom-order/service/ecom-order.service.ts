import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomOrder, getEcomOrderIdentifier } from '../ecom-order.model';

export type EntityResponseType = HttpResponse<IEcomOrder>;
export type EntityArrayResponseType = HttpResponse<IEcomOrder[]>;

@Injectable({ providedIn: 'root' })
export class EcomOrderService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-orders');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomOrder: IEcomOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ecomOrder);
    return this.http
      .post<IEcomOrder>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(ecomOrder: IEcomOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ecomOrder);
    return this.http
      .put<IEcomOrder>(`${this.resourceUrl}/${getEcomOrderIdentifier(ecomOrder) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(ecomOrder: IEcomOrder): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ecomOrder);
    return this.http
      .patch<IEcomOrder>(`${this.resourceUrl}/${getEcomOrderIdentifier(ecomOrder) as number}`, copy, { observe: 'response' })
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

  addEcomOrderToCollectionIfMissing(
    ecomOrderCollection: IEcomOrder[],
    ...ecomOrdersToCheck: (IEcomOrder | null | undefined)[]
  ): IEcomOrder[] {
    const ecomOrders: IEcomOrder[] = ecomOrdersToCheck.filter(isPresent);
    if (ecomOrders.length > 0) {
      const ecomOrderCollectionIdentifiers = ecomOrderCollection.map(ecomOrderItem => getEcomOrderIdentifier(ecomOrderItem)!);
      const ecomOrdersToAdd = ecomOrders.filter(ecomOrderItem => {
        const ecomOrderIdentifier = getEcomOrderIdentifier(ecomOrderItem);
        if (ecomOrderIdentifier == null || ecomOrderCollectionIdentifiers.includes(ecomOrderIdentifier)) {
          return false;
        }
        ecomOrderCollectionIdentifiers.push(ecomOrderIdentifier);
        return true;
      });
      return [...ecomOrdersToAdd, ...ecomOrderCollection];
    }
    return ecomOrderCollection;
  }

  protected convertDateFromClient(ecomOrder: IEcomOrder): IEcomOrder {
    return Object.assign({}, ecomOrder, {
      createdDate: ecomOrder.createdDate?.isValid() ? ecomOrder.createdDate.format(DATE_FORMAT) : undefined,
      updatedDate: ecomOrder.updatedDate?.isValid() ? ecomOrder.updatedDate.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.createdDate = res.body.createdDate ? dayjs(res.body.createdDate) : undefined;
      res.body.updatedDate = res.body.updatedDate ? dayjs(res.body.updatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((ecomOrder: IEcomOrder) => {
        ecomOrder.createdDate = ecomOrder.createdDate ? dayjs(ecomOrder.createdDate) : undefined;
        ecomOrder.updatedDate = ecomOrder.updatedDate ? dayjs(ecomOrder.updatedDate) : undefined;
      });
    }
    return res;
  }
}
