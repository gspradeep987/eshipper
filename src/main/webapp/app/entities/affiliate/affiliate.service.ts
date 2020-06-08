import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAffiliate } from 'app/shared/model/affiliate.model';

type EntityResponseType = HttpResponse<IAffiliate>;
type EntityArrayResponseType = HttpResponse<IAffiliate[]>;

@Injectable({ providedIn: 'root' })
export class AffiliateService {
  public resourceUrl = SERVER_API_URL + 'api/affiliates';

  constructor(protected http: HttpClient) {}

  create(affiliate: IAffiliate): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(affiliate);
    return this.http
      .post<IAffiliate>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(affiliate: IAffiliate): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(affiliate);
    return this.http
      .put<IAffiliate>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAffiliate>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAffiliate[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(affiliate: IAffiliate): IAffiliate {
    const copy: IAffiliate = Object.assign({}, affiliate, {
      commissionDate:
        affiliate.commissionDate && affiliate.commissionDate.isValid() ? affiliate.commissionDate.format(DATE_FORMAT) : undefined,
      createdDate: affiliate.createdDate && affiliate.createdDate.isValid() ? affiliate.createdDate.format(DATE_FORMAT) : undefined,
      updatedDate: affiliate.updatedDate && affiliate.updatedDate.isValid() ? affiliate.updatedDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.commissionDate = res.body.commissionDate ? moment(res.body.commissionDate) : undefined;
      res.body.createdDate = res.body.createdDate ? moment(res.body.createdDate) : undefined;
      res.body.updatedDate = res.body.updatedDate ? moment(res.body.updatedDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((affiliate: IAffiliate) => {
        affiliate.commissionDate = affiliate.commissionDate ? moment(affiliate.commissionDate) : undefined;
        affiliate.createdDate = affiliate.createdDate ? moment(affiliate.createdDate) : undefined;
        affiliate.updatedDate = affiliate.updatedDate ? moment(affiliate.updatedDate) : undefined;
      });
    }
    return res;
  }
}
