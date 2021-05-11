import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISisyphusJob, SisyphusJob } from '../sisyphus-job.model';

import { SisyphusJobService } from './sisyphus-job.service';

describe('Service Tests', () => {
  describe('SisyphusJob Service', () => {
    let service: SisyphusJobService;
    let httpMock: HttpTestingController;
    let elemDefault: ISisyphusJob;
    let expectedResult: ISisyphusJob | ISisyphusJob[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(SisyphusJobService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        name: 'AAAAAAA',
        scheduleMinute: 'AAAAAAA',
        scheduleHour: 'AAAAAAA',
        scheduleDay: 'AAAAAAA',
        scheduleMonth: 'AAAAAAA',
        shouldrunYN: 'AAAAAAA',
        retries: 0,
        moniterScheduleYN: false,
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

      it('should create a SisyphusJob', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SisyphusJob()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SisyphusJob', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            scheduleMinute: 'BBBBBB',
            scheduleHour: 'BBBBBB',
            scheduleDay: 'BBBBBB',
            scheduleMonth: 'BBBBBB',
            shouldrunYN: 'BBBBBB',
            retries: 1,
            moniterScheduleYN: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a SisyphusJob', () => {
        const patchObject = Object.assign(
          {
            name: 'BBBBBB',
            scheduleDay: 'BBBBBB',
            retries: 1,
            moniterScheduleYN: true,
          },
          new SisyphusJob()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SisyphusJob', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            scheduleMinute: 'BBBBBB',
            scheduleHour: 'BBBBBB',
            scheduleDay: 'BBBBBB',
            scheduleMonth: 'BBBBBB',
            shouldrunYN: 'BBBBBB',
            retries: 1,
            moniterScheduleYN: true,
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

      it('should delete a SisyphusJob', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addSisyphusJobToCollectionIfMissing', () => {
        it('should add a SisyphusJob to an empty array', () => {
          const sisyphusJob: ISisyphusJob = { id: 123 };
          expectedResult = service.addSisyphusJobToCollectionIfMissing([], sisyphusJob);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(sisyphusJob);
        });

        it('should not add a SisyphusJob to an array that contains it', () => {
          const sisyphusJob: ISisyphusJob = { id: 123 };
          const sisyphusJobCollection: ISisyphusJob[] = [
            {
              ...sisyphusJob,
            },
            { id: 456 },
          ];
          expectedResult = service.addSisyphusJobToCollectionIfMissing(sisyphusJobCollection, sisyphusJob);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a SisyphusJob to an array that doesn't contain it", () => {
          const sisyphusJob: ISisyphusJob = { id: 123 };
          const sisyphusJobCollection: ISisyphusJob[] = [{ id: 456 }];
          expectedResult = service.addSisyphusJobToCollectionIfMissing(sisyphusJobCollection, sisyphusJob);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(sisyphusJob);
        });

        it('should add only unique SisyphusJob to an array', () => {
          const sisyphusJobArray: ISisyphusJob[] = [{ id: 123 }, { id: 456 }, { id: 11231 }];
          const sisyphusJobCollection: ISisyphusJob[] = [{ id: 123 }];
          expectedResult = service.addSisyphusJobToCollectionIfMissing(sisyphusJobCollection, ...sisyphusJobArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const sisyphusJob: ISisyphusJob = { id: 123 };
          const sisyphusJob2: ISisyphusJob = { id: 456 };
          expectedResult = service.addSisyphusJobToCollectionIfMissing([], sisyphusJob, sisyphusJob2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(sisyphusJob);
          expect(expectedResult).toContain(sisyphusJob2);
        });

        it('should accept null and undefined values', () => {
          const sisyphusJob: ISisyphusJob = { id: 123 };
          expectedResult = service.addSisyphusJobToCollectionIfMissing([], null, sisyphusJob, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(sisyphusJob);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
