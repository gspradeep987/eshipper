import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWoSalesCommissionTransfer } from 'app/shared/model/wo-sales-commission-transfer.model';

type EntityResponseType = HttpResponse<IWoSalesCommissionTransfer>;
type EntityArrayResponseType = HttpResponse<IWoSalesCommissionTransfer[]>;

@Injectable({ providedIn: 'root' })
export class WoSalesCommissionTransferService {
  public resourceUrl = SERVER_API_URL + 'api/wo-sales-commission-transfers';

  constructor(protected http: HttpClient) {}

  create(woSalesCommissionTransfer: IWoSalesCommissionTransfer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(woSalesCommissionTransfer);
    return this.http
      .post<IWoSalesCommissionTransfer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(woSalesCommissionTransfer: IWoSalesCommissionTransfer): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(woSalesCommissionTransfer);
    return this.http
      .put<IWoSalesCommissionTransfer>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWoSalesCommissionTransfer>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWoSalesCommissionTransfer[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(woSalesCommissionTransfer: IWoSalesCommissionTransfer): IWoSalesCommissionTransfer {
    const copy: IWoSalesCommissionTransfer = Object.assign({}, woSalesCommissionTransfer, {
      customerTransferDate:
        woSalesCommissionTransfer.customerTransferDate && woSalesCommissionTransfer.customerTransferDate.isValid()
          ? woSalesCommissionTransfer.customerTransferDate.format(DATE_FORMAT)
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.customerTransferDate = res.body.customerTransferDate ? moment(res.body.customerTransferDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((woSalesCommissionTransfer: IWoSalesCommissionTransfer) => {
        woSalesCommissionTransfer.customerTransferDate = woSalesCommissionTransfer.customerTransferDate
          ? moment(woSalesCommissionTransfer.customerTransferDate)
          : undefined;
      });
    }
    return res;
  }
}
