import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEcomStore } from 'app/shared/model/ecom-store.model';

type EntityResponseType = HttpResponse<IEcomStore>;
type EntityArrayResponseType = HttpResponse<IEcomStore[]>;

@Injectable({ providedIn: 'root' })
export class EcomStoreService {
  public resourceUrl = SERVER_API_URL + 'api/ecom-stores';

  constructor(protected http: HttpClient) {}

  create(ecomStore: IEcomStore): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ecomStore);
    return this.http
      .post<IEcomStore>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(ecomStore: IEcomStore): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ecomStore);
    return this.http
      .put<IEcomStore>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IEcomStore>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IEcomStore[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(ecomStore: IEcomStore): IEcomStore {
    const copy: IEcomStore = Object.assign({}, ecomStore, {
      createdDate: ecomStore.createdDate && ecomStore.createdDate.isValid() ? ecomStore.createdDate.format(DATE_FORMAT) : undefined,
      updatedDate: ecomStore.updatedDate && ecomStore.updatedDate.isValid() ? ecomStore.updatedDate.format(DATE_FORMAT) : undefined
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
      res.body.forEach((ecomStore: IEcomStore) => {
        ecomStore.createdDate = ecomStore.createdDate ? moment(ecomStore.createdDate) : undefined;
        ecomStore.updatedDate = ecomStore.updatedDate ? moment(ecomStore.updatedDate) : undefined;
      });
    }
    return res;
  }
}
