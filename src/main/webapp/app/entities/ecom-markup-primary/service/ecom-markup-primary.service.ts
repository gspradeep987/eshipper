import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomMarkupPrimary, getEcomMarkupPrimaryIdentifier } from '../ecom-markup-primary.model';

export type EntityResponseType = HttpResponse<IEcomMarkupPrimary>;
export type EntityArrayResponseType = HttpResponse<IEcomMarkupPrimary[]>;

@Injectable({ providedIn: 'root' })
export class EcomMarkupPrimaryService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-markup-primaries');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomMarkupPrimary: IEcomMarkupPrimary): Observable<EntityResponseType> {
    return this.http.post<IEcomMarkupPrimary>(this.resourceUrl, ecomMarkupPrimary, { observe: 'response' });
  }

  update(ecomMarkupPrimary: IEcomMarkupPrimary): Observable<EntityResponseType> {
    return this.http.put<IEcomMarkupPrimary>(
      `${this.resourceUrl}/${getEcomMarkupPrimaryIdentifier(ecomMarkupPrimary) as number}`,
      ecomMarkupPrimary,
      { observe: 'response' }
    );
  }

  partialUpdate(ecomMarkupPrimary: IEcomMarkupPrimary): Observable<EntityResponseType> {
    return this.http.patch<IEcomMarkupPrimary>(
      `${this.resourceUrl}/${getEcomMarkupPrimaryIdentifier(ecomMarkupPrimary) as number}`,
      ecomMarkupPrimary,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomMarkupPrimary>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomMarkupPrimary[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEcomMarkupPrimaryToCollectionIfMissing(
    ecomMarkupPrimaryCollection: IEcomMarkupPrimary[],
    ...ecomMarkupPrimariesToCheck: (IEcomMarkupPrimary | null | undefined)[]
  ): IEcomMarkupPrimary[] {
    const ecomMarkupPrimaries: IEcomMarkupPrimary[] = ecomMarkupPrimariesToCheck.filter(isPresent);
    if (ecomMarkupPrimaries.length > 0) {
      const ecomMarkupPrimaryCollectionIdentifiers = ecomMarkupPrimaryCollection.map(
        ecomMarkupPrimaryItem => getEcomMarkupPrimaryIdentifier(ecomMarkupPrimaryItem)!
      );
      const ecomMarkupPrimariesToAdd = ecomMarkupPrimaries.filter(ecomMarkupPrimaryItem => {
        const ecomMarkupPrimaryIdentifier = getEcomMarkupPrimaryIdentifier(ecomMarkupPrimaryItem);
        if (ecomMarkupPrimaryIdentifier == null || ecomMarkupPrimaryCollectionIdentifiers.includes(ecomMarkupPrimaryIdentifier)) {
          return false;
        }
        ecomMarkupPrimaryCollectionIdentifiers.push(ecomMarkupPrimaryIdentifier);
        return true;
      });
      return [...ecomMarkupPrimariesToAdd, ...ecomMarkupPrimaryCollection];
    }
    return ecomMarkupPrimaryCollection;
  }
}
