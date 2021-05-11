import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IEcomStore, EcomStore } from '../ecom-store.model';

import { EcomStoreService } from './ecom-store.service';

describe('Service Tests', () => {
  describe('EcomStore Service', () => {
    let service: EcomStoreService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomStore;
    let expectedResult: IEcomStore | IEcomStore[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomStoreService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        name: 'AAAAAAA',
        nickName: 'AAAAAAA',
        apiPassword: 'AAAAAAA',
        domain: 'AAAAAAA',
        syncFrequency: 0,
        syncInterval: 'AAAAAAA',
        active: false,
        isManualSync: false,
        createdDate: currentDate,
        updatedDate: currentDate,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdDate: currentDate.format(DATE_FORMAT),
            updatedDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EcomStore', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdDate: currentDate.format(DATE_FORMAT),
            updatedDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            updatedDate: currentDate,
          },
          returnedFromService
        );

        service.create(new EcomStore()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomStore', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            nickName: 'BBBBBB',
            apiPassword: 'BBBBBB',
            domain: 'BBBBBB',
            syncFrequency: 1,
            syncInterval: 'BBBBBB',
            active: true,
            isManualSync: true,
            createdDate: currentDate.format(DATE_FORMAT),
            updatedDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            updatedDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomStore', () => {
        const patchObject = Object.assign(
          {
            name: 'BBBBBB',
            nickName: 'BBBBBB',
            isManualSync: true,
            createdDate: currentDate.format(DATE_FORMAT),
          },
          new EcomStore()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            createdDate: currentDate,
            updatedDate: currentDate,
          },
          returnedFromService
        );

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomStore', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            nickName: 'BBBBBB',
            apiPassword: 'BBBBBB',
            domain: 'BBBBBB',
            syncFrequency: 1,
            syncInterval: 'BBBBBB',
            active: true,
            isManualSync: true,
            createdDate: currentDate.format(DATE_FORMAT),
            updatedDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            updatedDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a EcomStore', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomStoreToCollectionIfMissing', () => {
        it('should add a EcomStore to an empty array', () => {
          const ecomStore: IEcomStore = { id: 123 };
          expectedResult = service.addEcomStoreToCollectionIfMissing([], ecomStore);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStore);
        });

        it('should not add a EcomStore to an array that contains it', () => {
          const ecomStore: IEcomStore = { id: 123 };
          const ecomStoreCollection: IEcomStore[] = [
            {
              ...ecomStore,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomStoreToCollectionIfMissing(ecomStoreCollection, ecomStore);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomStore to an array that doesn't contain it", () => {
          const ecomStore: IEcomStore = { id: 123 };
          const ecomStoreCollection: IEcomStore[] = [{ id: 456 }];
          expectedResult = service.addEcomStoreToCollectionIfMissing(ecomStoreCollection, ecomStore);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStore);
        });

        it('should add only unique EcomStore to an array', () => {
          const ecomStoreArray: IEcomStore[] = [{ id: 123 }, { id: 456 }, { id: 17932 }];
          const ecomStoreCollection: IEcomStore[] = [{ id: 123 }];
          expectedResult = service.addEcomStoreToCollectionIfMissing(ecomStoreCollection, ...ecomStoreArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomStore: IEcomStore = { id: 123 };
          const ecomStore2: IEcomStore = { id: 456 };
          expectedResult = service.addEcomStoreToCollectionIfMissing([], ecomStore, ecomStore2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStore);
          expect(expectedResult).toContain(ecomStore2);
        });

        it('should accept null and undefined values', () => {
          const ecomStore: IEcomStore = { id: 123 };
          expectedResult = service.addEcomStoreToCollectionIfMissing([], null, ecomStore, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStore);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
