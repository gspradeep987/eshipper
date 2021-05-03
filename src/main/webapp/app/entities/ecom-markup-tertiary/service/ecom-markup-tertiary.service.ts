import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomMarkupTertiary, getEcomMarkupTertiaryIdentifier } from '../ecom-markup-tertiary.model';

export type EntityResponseType = HttpResponse<IEcomMarkupTertiary>;
export type EntityArrayResponseType = HttpResponse<IEcomMarkupTertiary[]>;

@Injectable({ providedIn: 'root' })
export class EcomMarkupTertiaryService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-markup-tertiaries');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomMarkupTertiary: IEcomMarkupTertiary): Observable<EntityResponseType> {
    return this.http.post<IEcomMarkupTertiary>(this.resourceUrl, ecomMarkupTertiary, { observe: 'response' });
  }

  update(ecomMarkupTertiary: IEcomMarkupTertiary): Observable<EntityResponseType> {
    return this.http.put<IEcomMarkupTertiary>(
      `${this.resourceUrl}/${getEcomMarkupTertiaryIdentifier(ecomMarkupTertiary) as number}`,
      ecomMarkupTertiary,
      { observe: 'response' }
    );
  }

  partialUpdate(ecomMarkupTertiary: IEcomMarkupTertiary): Observable<EntityResponseType> {
    return this.http.patch<IEcomMarkupTertiary>(
      `${this.resourceUrl}/${getEcomMarkupTertiaryIdentifier(ecomMarkupTertiary) as number}`,
      ecomMarkupTertiary,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomMarkupTertiary>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomMarkupTertiary[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEcomMarkupTertiaryToCollectionIfMissing(
    ecomMarkupTertiaryCollection: IEcomMarkupTertiary[],
    ...ecomMarkupTertiariesToCheck: (IEcomMarkupTertiary | null | undefined)[]
  ): IEcomMarkupTertiary[] {
    const ecomMarkupTertiaries: IEcomMarkupTertiary[] = ecomMarkupTertiariesToCheck.filter(isPresent);
    if (ecomMarkupTertiaries.length > 0) {
      const ecomMarkupTertiaryCollectionIdentifiers = ecomMarkupTertiaryCollection.map(
        ecomMarkupTertiaryItem => getEcomMarkupTertiaryIdentifier(ecomMarkupTertiaryItem)!
      );
      const ecomMarkupTertiariesToAdd = ecomMarkupTertiaries.filter(ecomMarkupTertiaryItem => {
        const ecomMarkupTertiaryIdentifier = getEcomMarkupTertiaryIdentifier(ecomMarkupTertiaryItem);
        if (ecomMarkupTertiaryIdentifier == null || ecomMarkupTertiaryCollectionIdentifiers.includes(ecomMarkupTertiaryIdentifier)) {
          return false;
        }
        ecomMarkupTertiaryCollectionIdentifiers.push(ecomMarkupTertiaryIdentifier);
        return true;
      });
      return [...ecomMarkupTertiariesToAdd, ...ecomMarkupTertiaryCollection];
    }
    return ecomMarkupTertiaryCollection;
  }
}
