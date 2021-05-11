import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISisyphusSubJob, SisyphusSubJob } from '../sisyphus-sub-job.model';

import { SisyphusSubJobService } from './sisyphus-sub-job.service';

describe('Service Tests', () => {
  describe('SisyphusSubJob Service', () => {
    let service: SisyphusSubJobService;
    let httpMock: HttpTestingController;
    let elemDefault: ISisyphusSubJob;
    let expectedResult: ISisyphusSubJob | ISisyphusSubJob[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(SisyphusSubJobService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        runOrder: 'AAAAAAA',
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

      it('should create a SisyphusSubJob', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SisyphusSubJob()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SisyphusSubJob', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            runOrder: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a SisyphusSubJob', () => {
        const patchObject = Object.assign({}, new SisyphusSubJob());

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SisyphusSubJob', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            runOrder: 'BBBBBB',
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

      it('should delete a SisyphusSubJob', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addSisyphusSubJobToCollectionIfMissing', () => {
        it('should add a SisyphusSubJob to an empty array', () => {
          const sisyphusSubJob: ISisyphusSubJob = { id: 123 };
          expectedResult = service.addSisyphusSubJobToCollectionIfMissing([], sisyphusSubJob);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(sisyphusSubJob);
        });

        it('should not add a SisyphusSubJob to an array that contains it', () => {
          const sisyphusSubJob: ISisyphusSubJob = { id: 123 };
          const sisyphusSubJobCollection: ISisyphusSubJob[] = [
            {
              ...sisyphusSubJob,
            },
            { id: 456 },
          ];
          expectedResult = service.addSisyphusSubJobToCollectionIfMissing(sisyphusSubJobCollection, sisyphusSubJob);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a SisyphusSubJob to an array that doesn't contain it", () => {
          const sisyphusSubJob: ISisyphusSubJob = { id: 123 };
          const sisyphusSubJobCollection: ISisyphusSubJob[] = [{ id: 456 }];
          expectedResult = service.addSisyphusSubJobToCollectionIfMissing(sisyphusSubJobCollection, sisyphusSubJob);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(sisyphusSubJob);
        });

        it('should add only unique SisyphusSubJob to an array', () => {
          const sisyphusSubJobArray: ISisyphusSubJob[] = [{ id: 123 }, { id: 456 }, { id: 70968 }];
          const sisyphusSubJobCollection: ISisyphusSubJob[] = [{ id: 123 }];
          expectedResult = service.addSisyphusSubJobToCollectionIfMissing(sisyphusSubJobCollection, ...sisyphusSubJobArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const sisyphusSubJob: ISisyphusSubJob = { id: 123 };
          const sisyphusSubJob2: ISisyphusSubJob = { id: 456 };
          expectedResult = service.addSisyphusSubJobToCollectionIfMissing([], sisyphusSubJob, sisyphusSubJob2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(sisyphusSubJob);
          expect(expectedResult).toContain(sisyphusSubJob2);
        });

        it('should accept null and undefined values', () => {
          const sisyphusSubJob: ISisyphusSubJob = { id: 123 };
          expectedResult = service.addSisyphusSubJobToCollectionIfMissing([], null, sisyphusSubJob, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(sisyphusSubJob);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
