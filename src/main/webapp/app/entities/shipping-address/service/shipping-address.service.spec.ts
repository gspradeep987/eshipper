import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IShippingAddress, ShippingAddress } from '../shipping-address.model';

import { ShippingAddressService } from './shipping-address.service';

describe('Service Tests', () => {
  describe('ShippingAddress Service', () => {
    let service: ShippingAddressService;
    let httpMock: HttpTestingController;
    let elemDefault: IShippingAddress;
    let expectedResult: IShippingAddress | IShippingAddress[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(ShippingAddressService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
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

      it('should create a ShippingAddress', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ShippingAddress()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ShippingAddress', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a ShippingAddress', () => {
        const patchObject = Object.assign({}, new ShippingAddress());

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ShippingAddress', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
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

      it('should delete a ShippingAddress', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addShippingAddressToCollectionIfMissing', () => {
        it('should add a ShippingAddress to an empty array', () => {
          const shippingAddress: IShippingAddress = { id: 123 };
          expectedResult = service.addShippingAddressToCollectionIfMissing([], shippingAddress);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(shippingAddress);
        });

        it('should not add a ShippingAddress to an array that contains it', () => {
          const shippingAddress: IShippingAddress = { id: 123 };
          const shippingAddressCollection: IShippingAddress[] = [
            {
              ...shippingAddress,
            },
            { id: 456 },
          ];
          expectedResult = service.addShippingAddressToCollectionIfMissing(shippingAddressCollection, shippingAddress);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a ShippingAddress to an array that doesn't contain it", () => {
          const shippingAddress: IShippingAddress = { id: 123 };
          const shippingAddressCollection: IShippingAddress[] = [{ id: 456 }];
          expectedResult = service.addShippingAddressToCollectionIfMissing(shippingAddressCollection, shippingAddress);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(shippingAddress);
        });

        it('should add only unique ShippingAddress to an array', () => {
          const shippingAddressArray: IShippingAddress[] = [{ id: 123 }, { id: 456 }, { id: 59233 }];
          const shippingAddressCollection: IShippingAddress[] = [{ id: 123 }];
          expectedResult = service.addShippingAddressToCollectionIfMissing(shippingAddressCollection, ...shippingAddressArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const shippingAddress: IShippingAddress = { id: 123 };
          const shippingAddress2: IShippingAddress = { id: 456 };
          expectedResult = service.addShippingAddressToCollectionIfMissing([], shippingAddress, shippingAddress2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(shippingAddress);
          expect(expectedResult).toContain(shippingAddress2);
        });

        it('should accept null and undefined values', () => {
          const shippingAddress: IShippingAddress = { id: 123 };
          expectedResult = service.addShippingAddressToCollectionIfMissing([], null, shippingAddress, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(shippingAddress);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
