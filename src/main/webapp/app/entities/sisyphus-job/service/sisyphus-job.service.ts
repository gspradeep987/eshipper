import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ISisyphusJob, getSisyphusJobIdentifier } from '../sisyphus-job.model';

export type EntityResponseType = HttpResponse<ISisyphusJob>;
export type EntityArrayResponseType = HttpResponse<ISisyphusJob[]>;

@Injectable({ providedIn: 'root' })
export class SisyphusJobService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/sisyphus-jobs');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(sisyphusJob: ISisyphusJob): Observable<EntityResponseType> {
    return this.http.post<ISisyphusJob>(this.resourceUrl, sisyphusJob, { observe: 'response' });
  }

  update(sisyphusJob: ISisyphusJob): Observable<EntityResponseType> {
    return this.http.put<ISisyphusJob>(`${this.resourceUrl}/${getSisyphusJobIdentifier(sisyphusJob) as number}`, sisyphusJob, {
      observe: 'response',
    });
  }

  partialUpdate(sisyphusJob: ISisyphusJob): Observable<EntityResponseType> {
    return this.http.patch<ISisyphusJob>(`${this.resourceUrl}/${getSisyphusJobIdentifier(sisyphusJob) as number}`, sisyphusJob, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISisyphusJob>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISisyphusJob[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addSisyphusJobToCollectionIfMissing(
    sisyphusJobCollection: ISisyphusJob[],
    ...sisyphusJobsToCheck: (ISisyphusJob | null | undefined)[]
  ): ISisyphusJob[] {
    const sisyphusJobs: ISisyphusJob[] = sisyphusJobsToCheck.filter(isPresent);
    if (sisyphusJobs.length > 0) {
      const sisyphusJobCollectionIdentifiers = sisyphusJobCollection.map(sisyphusJobItem => getSisyphusJobIdentifier(sisyphusJobItem)!);
      const sisyphusJobsToAdd = sisyphusJobs.filter(sisyphusJobItem => {
        const sisyphusJobIdentifier = getSisyphusJobIdentifier(sisyphusJobItem);
        if (sisyphusJobIdentifier == null || sisyphusJobCollectionIdentifiers.includes(sisyphusJobIdentifier)) {
          return false;
        }
        sisyphusJobCollectionIdentifiers.push(sisyphusJobIdentifier);
        return true;
      });
      return [...sisyphusJobsToAdd, ...sisyphusJobCollection];
    }
    return sisyphusJobCollection;
  }
}
