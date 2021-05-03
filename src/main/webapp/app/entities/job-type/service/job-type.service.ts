import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IJobType, getJobTypeIdentifier } from '../job-type.model';

export type EntityResponseType = HttpResponse<IJobType>;
export type EntityArrayResponseType = HttpResponse<IJobType[]>;

@Injectable({ providedIn: 'root' })
export class JobTypeService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/job-types');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(jobType: IJobType): Observable<EntityResponseType> {
    return this.http.post<IJobType>(this.resourceUrl, jobType, { observe: 'response' });
  }

  update(jobType: IJobType): Observable<EntityResponseType> {
    return this.http.put<IJobType>(`${this.resourceUrl}/${getJobTypeIdentifier(jobType) as number}`, jobType, { observe: 'response' });
  }

  partialUpdate(jobType: IJobType): Observable<EntityResponseType> {
    return this.http.patch<IJobType>(`${this.resourceUrl}/${getJobTypeIdentifier(jobType) as number}`, jobType, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IJobType>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IJobType[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addJobTypeToCollectionIfMissing(jobTypeCollection: IJobType[], ...jobTypesToCheck: (IJobType | null | undefined)[]): IJobType[] {
    const jobTypes: IJobType[] = jobTypesToCheck.filter(isPresent);
    if (jobTypes.length > 0) {
      const jobTypeCollectionIdentifiers = jobTypeCollection.map(jobTypeItem => getJobTypeIdentifier(jobTypeItem)!);
      const jobTypesToAdd = jobTypes.filter(jobTypeItem => {
        const jobTypeIdentifier = getJobTypeIdentifier(jobTypeItem);
        if (jobTypeIdentifier == null || jobTypeCollectionIdentifiers.includes(jobTypeIdentifier)) {
          return false;
        }
        jobTypeCollectionIdentifiers.push(jobTypeIdentifier);
        return true;
      });
      return [...jobTypesToAdd, ...jobTypeCollection];
    }
    return jobTypeCollection;
  }
}
