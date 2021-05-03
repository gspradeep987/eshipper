import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomMarkupSecondary, getEcomMarkupSecondaryIdentifier } from '../ecom-markup-secondary.model';

export type EntityResponseType = HttpResponse<IEcomMarkupSecondary>;
export type EntityArrayResponseType = HttpResponse<IEcomMarkupSecondary[]>;

@Injectable({ providedIn: 'root' })
export class EcomMarkupSecondaryService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-markup-secondaries');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomMarkupSecondary: IEcomMarkupSecondary): Observable<EntityResponseType> {
    return this.http.post<IEcomMarkupSecondary>(this.resourceUrl, ecomMarkupSecondary, { observe: 'response' });
  }

  update(ecomMarkupSecondary: IEcomMarkupSecondary): Observable<EntityResponseType> {
    return this.http.put<IEcomMarkupSecondary>(
      `${this.resourceUrl}/${getEcomMarkupSecondaryIdentifier(ecomMarkupSecondary) as number}`,
      ecomMarkupSecondary,
      { observe: 'response' }
    );
  }

  partialUpdate(ecomMarkupSecondary: IEcomMarkupSecondary): Observable<EntityResponseType> {
    return this.http.patch<IEcomMarkupSecondary>(
      `${this.resourceUrl}/${getEcomMarkupSecondaryIdentifier(ecomMarkupSecondary) as number}`,
      ecomMarkupSecondary,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomMarkupSecondary>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomMarkupSecondary[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEcomMarkupSecondaryToCollectionIfMissing(
    ecomMarkupSecondaryCollection: IEcomMarkupSecondary[],
    ...ecomMarkupSecondariesToCheck: (IEcomMarkupSecondary | null | undefined)[]
  ): IEcomMarkupSecondary[] {
    const ecomMarkupSecondaries: IEcomMarkupSecondary[] = ecomMarkupSecondariesToCheck.filter(isPresent);
    if (ecomMarkupSecondaries.length > 0) {
      const ecomMarkupSecondaryCollectionIdentifiers = ecomMarkupSecondaryCollection.map(
        ecomMarkupSecondaryItem => getEcomMarkupSecondaryIdentifier(ecomMarkupSecondaryItem)!
      );
      const ecomMarkupSecondariesToAdd = ecomMarkupSecondaries.filter(ecomMarkupSecondaryItem => {
        const ecomMarkupSecondaryIdentifier = getEcomMarkupSecondaryIdentifier(ecomMarkupSecondaryItem);
        if (ecomMarkupSecondaryIdentifier == null || ecomMarkupSecondaryCollectionIdentifiers.includes(ecomMarkupSecondaryIdentifier)) {
          return false;
        }
        ecomMarkupSecondaryCollectionIdentifiers.push(ecomMarkupSecondaryIdentifier);
        return true;
      });
      return [...ecomMarkupSecondariesToAdd, ...ecomMarkupSecondaryCollection];
    }
    return ecomMarkupSecondaryCollection;
  }
}
