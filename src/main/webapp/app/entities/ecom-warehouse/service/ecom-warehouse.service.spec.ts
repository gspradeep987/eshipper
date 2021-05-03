import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomWarehouse, EcomWarehouse } from '../ecom-warehouse.model';

import { EcomWarehouseService } from './ecom-warehouse.service';

describe('Service Tests', () => {
  describe('EcomWarehouse Service', () => {
    let service: EcomWarehouseService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomWarehouse;
    let expectedResult: IEcomWarehouse | IEcomWarehouse[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomWarehouseService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        name: 'AAAAAAA',
        address: 'AAAAAAA',
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

      it('should create a EcomWarehouse', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomWarehouse()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomWarehouse', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            address: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomWarehouse', () => {
        const patchObject = Object.assign(
          {
            name: 'BBBBBB',
          },
          new EcomWarehouse()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomWarehouse', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            address: 'BBBBBB',
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

      it('should delete a EcomWarehouse', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomWarehouseToCollectionIfMissing', () => {
        it('should add a EcomWarehouse to an empty array', () => {
          const ecomWarehouse: IEcomWarehouse = { id: 123 };
          expectedResult = service.addEcomWarehouseToCollectionIfMissing([], ecomWarehouse);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomWarehouse);
        });

        it('should not add a EcomWarehouse to an array that contains it', () => {
          const ecomWarehouse: IEcomWarehouse = { id: 123 };
          const ecomWarehouseCollection: IEcomWarehouse[] = [
            {
              ...ecomWarehouse,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomWarehouseToCollectionIfMissing(ecomWarehouseCollection, ecomWarehouse);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomWarehouse to an array that doesn't contain it", () => {
          const ecomWarehouse: IEcomWarehouse = { id: 123 };
          const ecomWarehouseCollection: IEcomWarehouse[] = [{ id: 456 }];
          expectedResult = service.addEcomWarehouseToCollectionIfMissing(ecomWarehouseCollection, ecomWarehouse);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomWarehouse);
        });

        it('should add only unique EcomWarehouse to an array', () => {
          const ecomWarehouseArray: IEcomWarehouse[] = [{ id: 123 }, { id: 456 }, { id: 78527 }];
          const ecomWarehouseCollection: IEcomWarehouse[] = [{ id: 123 }];
          expectedResult = service.addEcomWarehouseToCollectionIfMissing(ecomWarehouseCollection, ...ecomWarehouseArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomWarehouse: IEcomWarehouse = { id: 123 };
          const ecomWarehouse2: IEcomWarehouse = { id: 456 };
          expectedResult = service.addEcomWarehouseToCollectionIfMissing([], ecomWarehouse, ecomWarehouse2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomWarehouse);
          expect(expectedResult).toContain(ecomWarehouse2);
        });

        it('should accept null and undefined values', () => {
          const ecomWarehouse: IEcomWarehouse = { id: 123 };
          expectedResult = service.addEcomWarehouseToCollectionIfMissing([], null, ecomWarehouse, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomWarehouse);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
