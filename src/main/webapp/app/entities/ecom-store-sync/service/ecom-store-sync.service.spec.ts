import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomStoreSync, EcomStoreSync } from '../ecom-store-sync.model';

import { EcomStoreSyncService } from './ecom-store-sync.service';

describe('Service Tests', () => {
  describe('EcomStoreSync Service', () => {
    let service: EcomStoreSyncService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomStoreSync;
    let expectedResult: IEcomStoreSync | IEcomStoreSync[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomStoreSyncService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        name: 'AAAAAAA',
        syncFrequency: 0,
        syncInterval: 'AAAAAAA',
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

      it('should create a EcomStoreSync', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomStoreSync()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomStoreSync', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            syncFrequency: 1,
            syncInterval: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomStoreSync', () => {
        const patchObject = Object.assign(
          {
            syncInterval: 'BBBBBB',
          },
          new EcomStoreSync()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomStoreSync', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            syncFrequency: 1,
            syncInterval: 'BBBBBB',
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

      it('should delete a EcomStoreSync', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomStoreSyncToCollectionIfMissing', () => {
        it('should add a EcomStoreSync to an empty array', () => {
          const ecomStoreSync: IEcomStoreSync = { id: 123 };
          expectedResult = service.addEcomStoreSyncToCollectionIfMissing([], ecomStoreSync);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStoreSync);
        });

        it('should not add a EcomStoreSync to an array that contains it', () => {
          const ecomStoreSync: IEcomStoreSync = { id: 123 };
          const ecomStoreSyncCollection: IEcomStoreSync[] = [
            {
              ...ecomStoreSync,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomStoreSyncToCollectionIfMissing(ecomStoreSyncCollection, ecomStoreSync);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomStoreSync to an array that doesn't contain it", () => {
          const ecomStoreSync: IEcomStoreSync = { id: 123 };
          const ecomStoreSyncCollection: IEcomStoreSync[] = [{ id: 456 }];
          expectedResult = service.addEcomStoreSyncToCollectionIfMissing(ecomStoreSyncCollection, ecomStoreSync);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStoreSync);
        });

        it('should add only unique EcomStoreSync to an array', () => {
          const ecomStoreSyncArray: IEcomStoreSync[] = [{ id: 123 }, { id: 456 }, { id: 86020 }];
          const ecomStoreSyncCollection: IEcomStoreSync[] = [{ id: 123 }];
          expectedResult = service.addEcomStoreSyncToCollectionIfMissing(ecomStoreSyncCollection, ...ecomStoreSyncArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomStoreSync: IEcomStoreSync = { id: 123 };
          const ecomStoreSync2: IEcomStoreSync = { id: 456 };
          expectedResult = service.addEcomStoreSyncToCollectionIfMissing([], ecomStoreSync, ecomStoreSync2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStoreSync);
          expect(expectedResult).toContain(ecomStoreSync2);
        });

        it('should accept null and undefined values', () => {
          const ecomStoreSync: IEcomStoreSync = { id: 123 };
          expectedResult = service.addEcomStoreSyncToCollectionIfMissing([], null, ecomStoreSync, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStoreSync);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
