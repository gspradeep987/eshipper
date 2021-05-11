import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomStoreAddress, EcomStoreAddress } from '../ecom-store-address.model';

import { EcomStoreAddressService } from './ecom-store-address.service';

describe('Service Tests', () => {
  describe('EcomStoreAddress Service', () => {
    let service: EcomStoreAddressService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomStoreAddress;
    let expectedResult: IEcomStoreAddress | IEcomStoreAddress[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomStoreAddressService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        address1: 'AAAAAAA',
        address2: 'AAAAAAA',
        name: 'AAAAAAA',
        phone: 'AAAAAAA',
        emailAccId: 'AAAAAAA',
        defaultAddress: false,
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

      it('should create a EcomStoreAddress', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomStoreAddress()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomStoreAddress', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            address1: 'BBBBBB',
            address2: 'BBBBBB',
            name: 'BBBBBB',
            phone: 'BBBBBB',
            emailAccId: 'BBBBBB',
            defaultAddress: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomStoreAddress', () => {
        const patchObject = Object.assign(
          {
            emailAccId: 'BBBBBB',
            defaultAddress: true,
          },
          new EcomStoreAddress()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomStoreAddress', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            address1: 'BBBBBB',
            address2: 'BBBBBB',
            name: 'BBBBBB',
            phone: 'BBBBBB',
            emailAccId: 'BBBBBB',
            defaultAddress: true,
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

      it('should delete a EcomStoreAddress', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomStoreAddressToCollectionIfMissing', () => {
        it('should add a EcomStoreAddress to an empty array', () => {
          const ecomStoreAddress: IEcomStoreAddress = { id: 123 };
          expectedResult = service.addEcomStoreAddressToCollectionIfMissing([], ecomStoreAddress);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStoreAddress);
        });

        it('should not add a EcomStoreAddress to an array that contains it', () => {
          const ecomStoreAddress: IEcomStoreAddress = { id: 123 };
          const ecomStoreAddressCollection: IEcomStoreAddress[] = [
            {
              ...ecomStoreAddress,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomStoreAddressToCollectionIfMissing(ecomStoreAddressCollection, ecomStoreAddress);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomStoreAddress to an array that doesn't contain it", () => {
          const ecomStoreAddress: IEcomStoreAddress = { id: 123 };
          const ecomStoreAddressCollection: IEcomStoreAddress[] = [{ id: 456 }];
          expectedResult = service.addEcomStoreAddressToCollectionIfMissing(ecomStoreAddressCollection, ecomStoreAddress);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStoreAddress);
        });

        it('should add only unique EcomStoreAddress to an array', () => {
          const ecomStoreAddressArray: IEcomStoreAddress[] = [{ id: 123 }, { id: 456 }, { id: 90731 }];
          const ecomStoreAddressCollection: IEcomStoreAddress[] = [{ id: 123 }];
          expectedResult = service.addEcomStoreAddressToCollectionIfMissing(ecomStoreAddressCollection, ...ecomStoreAddressArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomStoreAddress: IEcomStoreAddress = { id: 123 };
          const ecomStoreAddress2: IEcomStoreAddress = { id: 456 };
          expectedResult = service.addEcomStoreAddressToCollectionIfMissing([], ecomStoreAddress, ecomStoreAddress2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStoreAddress);
          expect(expectedResult).toContain(ecomStoreAddress2);
        });

        it('should accept null and undefined values', () => {
          const ecomStoreAddress: IEcomStoreAddress = { id: 123 };
          expectedResult = service.addEcomStoreAddressToCollectionIfMissing([], null, ecomStoreAddress, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStoreAddress);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
