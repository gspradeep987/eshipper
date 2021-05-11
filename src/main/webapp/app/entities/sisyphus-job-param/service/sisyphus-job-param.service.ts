import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISisyphusJobParam, getSisyphusJobParamIdentifier } from '../sisyphus-job-param.model';

export type EntityResponseType = HttpResponse<ISisyphusJobParam>;
export type EntityArrayResponseType = HttpResponse<ISisyphusJobParam[]>;

@Injectable({ providedIn: 'root' })
export class SisyphusJobParamService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/sisyphus-job-params');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(sisyphusJobParam: ISisyphusJobParam): Observable<EntityResponseType> {
    return this.http.post<ISisyphusJobParam>(this.resourceUrl, sisyphusJobParam, { observe: 'response' });
  }

  update(sisyphusJobParam: ISisyphusJobParam): Observable<EntityResponseType> {
    return this.http.put<ISisyphusJobParam>(
      `${this.resourceUrl}/${getSisyphusJobParamIdentifier(sisyphusJobParam) as number}`,
      sisyphusJobParam,
      { observe: 'response' }
    );
  }

  partialUpdate(sisyphusJobParam: ISisyphusJobParam): Observable<EntityResponseType> {
    return this.http.patch<ISisyphusJobParam>(
      `${this.resourceUrl}/${getSisyphusJobParamIdentifier(sisyphusJobParam) as number}`,
      sisyphusJobParam,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISisyphusJobParam>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISisyphusJobParam[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addSisyphusJobParamToCollectionIfMissing(
    sisyphusJobParamCollection: ISisyphusJobParam[],
    ...sisyphusJobParamsToCheck: (ISisyphusJobParam | null | undefined)[]
  ): ISisyphusJobParam[] {
    const sisyphusJobParams: ISisyphusJobParam[] = sisyphusJobParamsToCheck.filter(isPresent);
    if (sisyphusJobParams.length > 0) {
      const sisyphusJobParamCollectionIdentifiers = sisyphusJobParamCollection.map(
        sisyphusJobParamItem => getSisyphusJobParamIdentifier(sisyphusJobParamItem)!
      );
      const sisyphusJobParamsToAdd = sisyphusJobParams.filter(sisyphusJobParamItem => {
        const sisyphusJobParamIdentifier = getSisyphusJobParamIdentifier(sisyphusJobParamItem);
        if (sisyphusJobParamIdentifier == null || sisyphusJobParamCollectionIdentifiers.includes(sisyphusJobParamIdentifier)) {
          return false;
        }
        sisyphusJobParamCollectionIdentifiers.push(sisyphusJobParamIdentifier);
        return true;
      });
      return [...sisyphusJobParamsToAdd, ...sisyphusJobParamCollection];
    }
    return sisyphusJobParamCollection;
  }
}
