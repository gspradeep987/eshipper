import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IJobType, JobType } from '../job-type.model';

import { JobTypeService } from './job-type.service';

describe('Service Tests', () => {
  describe('JobType Service', () => {
    let service: JobTypeService;
    let httpMock: HttpTestingController;
    let elemDefault: IJobType;
    let expectedResult: IJobType | IJobType[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(JobTypeService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        iD: 0,
        nAME: 'AAAAAAA',
        dESCRIPTION: 'AAAAAAA',
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a JobType', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new JobType()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a JobType', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            iD: 1,
            nAME: 'BBBBBB',
            dESCRIPTION: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a JobType', () => {
        const patchObject = Object.assign(
          {
            iD: 1,
            nAME: 'BBBBBB',
          },
          new JobType()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of JobType', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            iD: 1,
            nAME: 'BBBBBB',
            dESCRIPTION: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a JobType', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addJobTypeToCollectionIfMissing', () => {
        it('should add a JobType to an empty array', () => {
          const jobType: IJobType = { id: 123 };
          expectedResult = service.addJobTypeToCollectionIfMissing([], jobType);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(jobType);
        });

        it('should not add a JobType to an array that contains it', () => {
          const jobType: IJobType = { id: 123 };
          const jobTypeCollection: IJobType[] = [
            {
              ...jobType,
            },
            { id: 456 },
          ];
          expectedResult = service.addJobTypeToCollectionIfMissing(jobTypeCollection, jobType);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a JobType to an array that doesn't contain it", () => {
          const jobType: IJobType = { id: 123 };
          const jobTypeCollection: IJobType[] = [{ id: 456 }];
          expectedResult = service.addJobTypeToCollectionIfMissing(jobTypeCollection, jobType);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(jobType);
        });

        it('should add only unique JobType to an array', () => {
          const jobTypeArray: IJobType[] = [{ id: 123 }, { id: 456 }, { id: 90875 }];
          const jobTypeCollection: IJobType[] = [{ id: 123 }];
          expectedResult = service.addJobTypeToCollectionIfMissing(jobTypeCollection, ...jobTypeArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const jobType: IJobType = { id: 123 };
          const jobType2: IJobType = { id: 456 };
          expectedResult = service.addJobTypeToCollectionIfMissing([], jobType, jobType2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(jobType);
          expect(expectedResult).toContain(jobType2);
        });

        it('should accept null and undefined values', () => {
          const jobType: IJobType = { id: 123 };
          expectedResult = service.addJobTypeToCollectionIfMissing([], null, jobType, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(jobType);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
