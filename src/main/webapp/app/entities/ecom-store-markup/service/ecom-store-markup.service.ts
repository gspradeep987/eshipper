import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomStoreMarkup, getEcomStoreMarkupIdentifier } from '../ecom-store-markup.model';

export type EntityResponseType = HttpResponse<IEcomStoreMarkup>;
export type EntityArrayResponseType = HttpResponse<IEcomStoreMarkup[]>;

@Injectable({ providedIn: 'root' })
export class EcomStoreMarkupService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-store-markups');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomStoreMarkup: IEcomStoreMarkup): Observable<EntityResponseType> {
    return this.http.post<IEcomStoreMarkup>(this.resourceUrl, ecomStoreMarkup, { observe: 'response' });
  }

  update(ecomStoreMarkup: IEcomStoreMarkup): Observable<EntityResponseType> {
    return this.http.put<IEcomStoreMarkup>(
      `${this.resourceUrl}/${getEcomStoreMarkupIdentifier(ecomStoreMarkup) as number}`,
      ecomStoreMarkup,
      { observe: 'response' }
    );
  }

  partialUpdate(ecomStoreMarkup: IEcomStoreMarkup): Observable<EntityResponseType> {
    return this.http.patch<IEcomStoreMarkup>(
      `${this.resourceUrl}/${getEcomStoreMarkupIdentifier(ecomStoreMarkup) as number}`,
      ecomStoreMarkup,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomStoreMarkup>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomStoreMarkup[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEcomStoreMarkupToCollectionIfMissing(
    ecomStoreMarkupCollection: IEcomStoreMarkup[],
    ...ecomStoreMarkupsToCheck: (IEcomStoreMarkup | null | undefined)[]
  ): IEcomStoreMarkup[] {
    const ecomStoreMarkups: IEcomStoreMarkup[] = ecomStoreMarkupsToCheck.filter(isPresent);
    if (ecomStoreMarkups.length > 0) {
      const ecomStoreMarkupCollectionIdentifiers = ecomStoreMarkupCollection.map(
        ecomStoreMarkupItem => getEcomStoreMarkupIdentifier(ecomStoreMarkupItem)!
      );
      const ecomStoreMarkupsToAdd = ecomStoreMarkups.filter(ecomStoreMarkupItem => {
        const ecomStoreMarkupIdentifier = getEcomStoreMarkupIdentifier(ecomStoreMarkupItem);
        if (ecomStoreMarkupIdentifier == null || ecomStoreMarkupCollectionIdentifiers.includes(ecomStoreMarkupIdentifier)) {
          return false;
        }
        ecomStoreMarkupCollectionIdentifiers.push(ecomStoreMarkupIdentifier);
        return true;
      });
      return [...ecomStoreMarkupsToAdd, ...ecomStoreMarkupCollection];
    }
    return ecomStoreMarkupCollection;
  }
}
