import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomMarkupQuaternary, getEcomMarkupQuaternaryIdentifier } from '../ecom-markup-quaternary.model';

export type EntityResponseType = HttpResponse<IEcomMarkupQuaternary>;
export type EntityArrayResponseType = HttpResponse<IEcomMarkupQuaternary[]>;

@Injectable({ providedIn: 'root' })
export class EcomMarkupQuaternaryService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-markup-quaternaries');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomMarkupQuaternary: IEcomMarkupQuaternary): Observable<EntityResponseType> {
    return this.http.post<IEcomMarkupQuaternary>(this.resourceUrl, ecomMarkupQuaternary, { observe: 'response' });
  }

  update(ecomMarkupQuaternary: IEcomMarkupQuaternary): Observable<EntityResponseType> {
    return this.http.put<IEcomMarkupQuaternary>(
      `${this.resourceUrl}/${getEcomMarkupQuaternaryIdentifier(ecomMarkupQuaternary) as number}`,
      ecomMarkupQuaternary,
      { observe: 'response' }
    );
  }

  partialUpdate(ecomMarkupQuaternary: IEcomMarkupQuaternary): Observable<EntityResponseType> {
    return this.http.patch<IEcomMarkupQuaternary>(
      `${this.resourceUrl}/${getEcomMarkupQuaternaryIdentifier(ecomMarkupQuaternary) as number}`,
      ecomMarkupQuaternary,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomMarkupQuaternary>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomMarkupQuaternary[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEcomMarkupQuaternaryToCollectionIfMissing(
    ecomMarkupQuaternaryCollection: IEcomMarkupQuaternary[],
    ...ecomMarkupQuaternariesToCheck: (IEcomMarkupQuaternary | null | undefined)[]
  ): IEcomMarkupQuaternary[] {
    const ecomMarkupQuaternaries: IEcomMarkupQuaternary[] = ecomMarkupQuaternariesToCheck.filter(isPresent);
    if (ecomMarkupQuaternaries.length > 0) {
      const ecomMarkupQuaternaryCollectionIdentifiers = ecomMarkupQuaternaryCollection.map(
        ecomMarkupQuaternaryItem => getEcomMarkupQuaternaryIdentifier(ecomMarkupQuaternaryItem)!
      );
      const ecomMarkupQuaternariesToAdd = ecomMarkupQuaternaries.filter(ecomMarkupQuaternaryItem => {
        const ecomMarkupQuaternaryIdentifier = getEcomMarkupQuaternaryIdentifier(ecomMarkupQuaternaryItem);
        if (ecomMarkupQuaternaryIdentifier == null || ecomMarkupQuaternaryCollectionIdentifiers.includes(ecomMarkupQuaternaryIdentifier)) {
          return false;
        }
        ecomMarkupQuaternaryCollectionIdentifiers.push(ecomMarkupQuaternaryIdentifier);
        return true;
      });
      return [...ecomMarkupQuaternariesToAdd, ...ecomMarkupQuaternaryCollection];
    }
    return ecomMarkupQuaternaryCollection;
  }
}
