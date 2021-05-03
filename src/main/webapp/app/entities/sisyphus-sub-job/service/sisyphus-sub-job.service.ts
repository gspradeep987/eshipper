import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISisyphusSubJob, getSisyphusSubJobIdentifier } from '../sisyphus-sub-job.model';

export type EntityResponseType = HttpResponse<ISisyphusSubJob>;
export type EntityArrayResponseType = HttpResponse<ISisyphusSubJob[]>;

@Injectable({ providedIn: 'root' })
export class SisyphusSubJobService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/sisyphus-sub-jobs');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(sisyphusSubJob: ISisyphusSubJob): Observable<EntityResponseType> {
    return this.http.post<ISisyphusSubJob>(this.resourceUrl, sisyphusSubJob, { observe: 'response' });
  }

  update(sisyphusSubJob: ISisyphusSubJob): Observable<EntityResponseType> {
    return this.http.put<ISisyphusSubJob>(`${this.resourceUrl}/${getSisyphusSubJobIdentifier(sisyphusSubJob) as number}`, sisyphusSubJob, {
      observe: 'response',
    });
  }

  partialUpdate(sisyphusSubJob: ISisyphusSubJob): Observable<EntityResponseType> {
    return this.http.patch<ISisyphusSubJob>(
      `${this.resourceUrl}/${getSisyphusSubJobIdentifier(sisyphusSubJob) as number}`,
      sisyphusSubJob,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISisyphusSubJob>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISisyphusSubJob[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addSisyphusSubJobToCollectionIfMissing(
    sisyphusSubJobCollection: ISisyphusSubJob[],
    ...sisyphusSubJobsToCheck: (ISisyphusSubJob | null | undefined)[]
  ): ISisyphusSubJob[] {
    const sisyphusSubJobs: ISisyphusSubJob[] = sisyphusSubJobsToCheck.filter(isPresent);
    if (sisyphusSubJobs.length > 0) {
      const sisyphusSubJobCollectionIdentifiers = sisyphusSubJobCollection.map(
        sisyphusSubJobItem => getSisyphusSubJobIdentifier(sisyphusSubJobItem)!
      );
      const sisyphusSubJobsToAdd = sisyphusSubJobs.filter(sisyphusSubJobItem => {
        const sisyphusSubJobIdentifier = getSisyphusSubJobIdentifier(sisyphusSubJobItem);
        if (sisyphusSubJobIdentifier == null || sisyphusSubJobCollectionIdentifiers.includes(sisyphusSubJobIdentifier)) {
          return false;
        }
        sisyphusSubJobCollectionIdentifiers.push(sisyphusSubJobIdentifier);
        return true;
      });
      return [...sisyphusSubJobsToAdd, ...sisyphusSubJobCollection];
    }
    return sisyphusSubJobCollection;
  }
}
