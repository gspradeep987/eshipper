import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomProduct, EcomProduct } from '../ecom-product.model';

import { EcomProductService } from './ecom-product.service';

describe('Service Tests', () => {
  describe('EcomProduct Service', () => {
    let service: EcomProductService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomProduct;
    let expectedResult: IEcomProduct | IEcomProduct[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomProductService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        title: 'AAAAAAA',
        length: 0,
        width: 0,
        height: 0,
        weight: 0,
        dimUnit: 'AAAAAAA',
        wghtUnit: 'AAAAAAA',
        goodsValue: 0,
        productValue: 0,
        hsCodes: 'AAAAAAA',
        sku: 'AAAAAAA',
        policy: 'AAAAAAA',
        insuranceAmt: 0,
        isInsured: false,
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

      it('should create a EcomProduct', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomProduct()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomProduct', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            title: 'BBBBBB',
            length: 1,
            width: 1,
            height: 1,
            weight: 1,
            dimUnit: 'BBBBBB',
            wghtUnit: 'BBBBBB',
            goodsValue: 1,
            productValue: 1,
            hsCodes: 'BBBBBB',
            sku: 'BBBBBB',
            policy: 'BBBBBB',
            insuranceAmt: 1,
            isInsured: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomProduct', () => {
        const patchObject = Object.assign(
          {
            width: 1,
            height: 1,
            weight: 1,
            goodsValue: 1,
            hsCodes: 'BBBBBB',
            sku: 'BBBBBB',
          },
          new EcomProduct()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomProduct', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            title: 'BBBBBB',
            length: 1,
            width: 1,
            height: 1,
            weight: 1,
            dimUnit: 'BBBBBB',
            wghtUnit: 'BBBBBB',
            goodsValue: 1,
            productValue: 1,
            hsCodes: 'BBBBBB',
            sku: 'BBBBBB',
            policy: 'BBBBBB',
            insuranceAmt: 1,
            isInsured: true,
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

      it('should delete a EcomProduct', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomProductToCollectionIfMissing', () => {
        it('should add a EcomProduct to an empty array', () => {
          const ecomProduct: IEcomProduct = { id: 123 };
          expectedResult = service.addEcomProductToCollectionIfMissing([], ecomProduct);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomProduct);
        });

        it('should not add a EcomProduct to an array that contains it', () => {
          const ecomProduct: IEcomProduct = { id: 123 };
          const ecomProductCollection: IEcomProduct[] = [
            {
              ...ecomProduct,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomProductToCollectionIfMissing(ecomProductCollection, ecomProduct);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomProduct to an array that doesn't contain it", () => {
          const ecomProduct: IEcomProduct = { id: 123 };
          const ecomProductCollection: IEcomProduct[] = [{ id: 456 }];
          expectedResult = service.addEcomProductToCollectionIfMissing(ecomProductCollection, ecomProduct);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomProduct);
        });

        it('should add only unique EcomProduct to an array', () => {
          const ecomProductArray: IEcomProduct[] = [{ id: 123 }, { id: 456 }, { id: 99949 }];
          const ecomProductCollection: IEcomProduct[] = [{ id: 123 }];
          expectedResult = service.addEcomProductToCollectionIfMissing(ecomProductCollection, ...ecomProductArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomProduct: IEcomProduct = { id: 123 };
          const ecomProduct2: IEcomProduct = { id: 456 };
          expectedResult = service.addEcomProductToCollectionIfMissing([], ecomProduct, ecomProduct2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomProduct);
          expect(expectedResult).toContain(ecomProduct2);
        });

        it('should accept null and undefined values', () => {
          const ecomProduct: IEcomProduct = { id: 123 };
          expectedResult = service.addEcomProductToCollectionIfMissing([], null, ecomProduct, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomProduct);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
