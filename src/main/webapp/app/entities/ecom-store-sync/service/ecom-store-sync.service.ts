import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomStoreSync, getEcomStoreSyncIdentifier } from '../ecom-store-sync.model';

export type EntityResponseType = HttpResponse<IEcomStoreSync>;
export type EntityArrayResponseType = HttpResponse<IEcomStoreSync[]>;

@Injectable({ providedIn: 'root' })
export class EcomStoreSyncService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-store-syncs');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomStoreSync: IEcomStoreSync): Observable<EntityResponseType> {
    return this.http.post<IEcomStoreSync>(this.resourceUrl, ecomStoreSync, { observe: 'response' });
  }

  update(ecomStoreSync: IEcomStoreSync): Observable<EntityResponseType> {
    return this.http.put<IEcomStoreSync>(`${this.resourceUrl}/${getEcomStoreSyncIdentifier(ecomStoreSync) as number}`, ecomStoreSync, {
      observe: 'response',
    });
  }

  partialUpdate(ecomStoreSync: IEcomStoreSync): Observable<EntityResponseType> {
    return this.http.patch<IEcomStoreSync>(`${this.resourceUrl}/${getEcomStoreSyncIdentifier(ecomStoreSync) as number}`, ecomStoreSync, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomStoreSync>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomStoreSync[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEcomStoreSyncToCollectionIfMissing(
    ecomStoreSyncCollection: IEcomStoreSync[],
    ...ecomStoreSyncsToCheck: (IEcomStoreSync | null | undefined)[]
  ): IEcomStoreSync[] {
    const ecomStoreSyncs: IEcomStoreSync[] = ecomStoreSyncsToCheck.filter(isPresent);
    if (ecomStoreSyncs.length > 0) {
      const ecomStoreSyncCollectionIdentifiers = ecomStoreSyncCollection.map(
        ecomStoreSyncItem => getEcomStoreSyncIdentifier(ecomStoreSyncItem)!
      );
      const ecomStoreSyncsToAdd = ecomStoreSyncs.filter(ecomStoreSyncItem => {
        const ecomStoreSyncIdentifier = getEcomStoreSyncIdentifier(ecomStoreSyncItem);
        if (ecomStoreSyncIdentifier == null || ecomStoreSyncCollectionIdentifiers.includes(ecomStoreSyncIdentifier)) {
          return false;
        }
        ecomStoreSyncCollectionIdentifiers.push(ecomStoreSyncIdentifier);
        return true;
      });
      return [...ecomStoreSyncsToAdd, ...ecomStoreSyncCollection];
    }
    return ecomStoreSyncCollection;
  }
}
