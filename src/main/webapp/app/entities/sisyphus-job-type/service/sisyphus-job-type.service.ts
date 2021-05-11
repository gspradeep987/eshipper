import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISisyphusJobType, getSisyphusJobTypeIdentifier } from '../sisyphus-job-type.model';

export type EntityResponseType = HttpResponse<ISisyphusJobType>;
export type EntityArrayResponseType = HttpResponse<ISisyphusJobType[]>;

@Injectable({ providedIn: 'root' })
export class SisyphusJobTypeService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/sisyphus-job-types');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(sisyphusJobType: ISisyphusJobType): Observable<EntityResponseType> {
    return this.http.post<ISisyphusJobType>(this.resourceUrl, sisyphusJobType, { observe: 'response' });
  }

  update(sisyphusJobType: ISisyphusJobType): Observable<EntityResponseType> {
    return this.http.put<ISisyphusJobType>(
      `${this.resourceUrl}/${getSisyphusJobTypeIdentifier(sisyphusJobType) as number}`,
      sisyphusJobType,
      { observe: 'response' }
    );
  }

  partialUpdate(sisyphusJobType: ISisyphusJobType): Observable<EntityResponseType> {
    return this.http.patch<ISisyphusJobType>(
      `${this.resourceUrl}/${getSisyphusJobTypeIdentifier(sisyphusJobType) as number}`,
      sisyphusJobType,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISisyphusJobType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISisyphusJobType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addSisyphusJobTypeToCollectionIfMissing(
    sisyphusJobTypeCollection: ISisyphusJobType[],
    ...sisyphusJobTypesToCheck: (ISisyphusJobType | null | undefined)[]
  ): ISisyphusJobType[] {
    const sisyphusJobTypes: ISisyphusJobType[] = sisyphusJobTypesToCheck.filter(isPresent);
    if (sisyphusJobTypes.length > 0) {
      const sisyphusJobTypeCollectionIdentifiers = sisyphusJobTypeCollection.map(
        sisyphusJobTypeItem => getSisyphusJobTypeIdentifier(sisyphusJobTypeItem)!
      );
      const sisyphusJobTypesToAdd = sisyphusJobTypes.filter(sisyphusJobTypeItem => {
        const sisyphusJobTypeIdentifier = getSisyphusJobTypeIdentifier(sisyphusJobTypeItem);
        if (sisyphusJobTypeIdentifier == null || sisyphusJobTypeCollectionIdentifiers.includes(sisyphusJobTypeIdentifier)) {
          return false;
        }
        sisyphusJobTypeCollectionIdentifiers.push(sisyphusJobTypeIdentifier);
        return true;
      });
      return [...sisyphusJobTypesToAdd, ...sisyphusJobTypeCollection];
    }
    return sisyphusJobTypeCollection;
  }
}
