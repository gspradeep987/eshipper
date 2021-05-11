import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IEcomStoreColorTheme, getEcomStoreColorThemeIdentifier } from '../ecom-store-color-theme.model';

export type EntityResponseType = HttpResponse<IEcomStoreColorTheme>;
export type EntityArrayResponseType = HttpResponse<IEcomStoreColorTheme[]>;

@Injectable({ providedIn: 'root' })
export class EcomStoreColorThemeService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/ecom-store-color-themes');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(ecomStoreColorTheme: IEcomStoreColorTheme): Observable<EntityResponseType> {
    return this.http.post<IEcomStoreColorTheme>(this.resourceUrl, ecomStoreColorTheme, { observe: 'response' });
  }

  update(ecomStoreColorTheme: IEcomStoreColorTheme): Observable<EntityResponseType> {
    return this.http.put<IEcomStoreColorTheme>(
      `${this.resourceUrl}/${getEcomStoreColorThemeIdentifier(ecomStoreColorTheme) as number}`,
      ecomStoreColorTheme,
      { observe: 'response' }
    );
  }

  partialUpdate(ecomStoreColorTheme: IEcomStoreColorTheme): Observable<EntityResponseType> {
    return this.http.patch<IEcomStoreColorTheme>(
      `${this.resourceUrl}/${getEcomStoreColorThemeIdentifier(ecomStoreColorTheme) as number}`,
      ecomStoreColorTheme,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEcomStoreColorTheme>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEcomStoreColorTheme[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addEcomStoreColorThemeToCollectionIfMissing(
    ecomStoreColorThemeCollection: IEcomStoreColorTheme[],
    ...ecomStoreColorThemesToCheck: (IEcomStoreColorTheme | null | undefined)[]
  ): IEcomStoreColorTheme[] {
    const ecomStoreColorThemes: IEcomStoreColorTheme[] = ecomStoreColorThemesToCheck.filter(isPresent);
    if (ecomStoreColorThemes.length > 0) {
      const ecomStoreColorThemeCollectionIdentifiers = ecomStoreColorThemeCollection.map(
        ecomStoreColorThemeItem => getEcomStoreColorThemeIdentifier(ecomStoreColorThemeItem)!
      );
      const ecomStoreColorThemesToAdd = ecomStoreColorThemes.filter(ecomStoreColorThemeItem => {
        const ecomStoreColorThemeIdentifier = getEcomStoreColorThemeIdentifier(ecomStoreColorThemeItem);
        if (ecomStoreColorThemeIdentifier == null || ecomStoreColorThemeCollectionIdentifiers.includes(ecomStoreColorThemeIdentifier)) {
          return false;
        }
        ecomStoreColorThemeCollectionIdentifiers.push(ecomStoreColorThemeIdentifier);
        return true;
      });
      return [...ecomStoreColorThemesToAdd, ...ecomStoreColorThemeCollection];
    }
    return ecomStoreColorThemeCollection;
  }
}
