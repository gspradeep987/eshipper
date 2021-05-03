import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as dayjs from 'dayjs';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomStore, getEcomStoreIdentifier } from '../ecom-store.model';

export type EntityResponseType = HttpResponse<IEcomStore>;
export type EntityArrayResponseType = HttpResponse<IEcomStore[]>;

@Injectable({ providedIn: 'root' })
export class EcomStoreService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-stores');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomStore: IEcomStore): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ecomStore);
    return this.http
      .post<IEcomStore>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(ecomStore: IEcomStore): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ecomStore);
    return this.http
      .put<IEcomStore>(`${this.resourceUrl}/${getEcomStoreIdentifier(ecomStore) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(ecomStore: IEcomStore): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ecomStore);
    return this.http
      .patch<IEcomStore>(`${this.resourceUrl}/${getEcomStoreIdentifier(ecomStore) as number}`, copy, { observe: 'response' })
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

  addEcomStoreToCollectionIfMissing(
    ecomStoreCollection: IEcomStore[],
    ...ecomStoresToCheck: (IEcomStore | null | undefined)[]
  ): IEcomStore[] {
    const ecomStores: IEcomStore[] = ecomStoresToCheck.filter(isPresent);
    if (ecomStores.length > 0) {
      const ecomStoreCollectionIdentifiers = ecomStoreCollection.map(ecomStoreItem => getEcomStoreIdentifier(ecomStoreItem)!);
      const ecomStoresToAdd = ecomStores.filter(ecomStoreItem => {
        const ecomStoreIdentifier = getEcomStoreIdentifier(ecomStoreItem);
        if (ecomStoreIdentifier == null || ecomStoreCollectionIdentifiers.includes(ecomStoreIdentifier)) {
          return false;
        }
        ecomStoreCollectionIdentifiers.push(ecomStoreIdentifier);
        return true;
      });
      return [...ecomStoresToAdd, ...ecomStoreCollection];
    }
    return ecomStoreCollection;
  }

  protected convertDateFromClient(ecomStore: IEcomStore): IEcomStore {
    return Object.assign({}, ecomStore, {
      createdDate: ecomStore.createdDate?.isValid() ? ecomStore.createdDate.format(DATE_FORMAT) : undefined,
      updatedDate: ecomStore.updatedDate?.isValid() ? ecomStore.updatedDate.format(DATE_FORMAT) : undefined,
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
      res.body.forEach((ecomStore: IEcomStore) => {
        ecomStore.createdDate = ecomStore.createdDate ? dayjs(ecomStore.createdDate) : undefined;
        ecomStore.updatedDate = ecomStore.updatedDate ? dayjs(ecomStore.updatedDate) : undefined;
      });
    }
    return res;
  }
}
