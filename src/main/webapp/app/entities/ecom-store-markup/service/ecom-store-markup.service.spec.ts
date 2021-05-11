import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomStoreMarkup, EcomStoreMarkup } from '../ecom-store-markup.model';

import { EcomStoreMarkupService } from './ecom-store-markup.service';

describe('Service Tests', () => {
  describe('EcomStoreMarkup Service', () => {
    let service: EcomStoreMarkupService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomStoreMarkup;
    let expectedResult: IEcomStoreMarkup | IEcomStoreMarkup[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomStoreMarkupService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        markupType: 'AAAAAAA',
        minValue: 0,
        domesticValue: 0,
        intlValue: 0,
        flatRateValue: 0,
        opexValue: 0,
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

      it('should create a EcomStoreMarkup', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomStoreMarkup()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomStoreMarkup', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            markupType: 'BBBBBB',
            minValue: 1,
            domesticValue: 1,
            intlValue: 1,
            flatRateValue: 1,
            opexValue: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomStoreMarkup', () => {
        const patchObject = Object.assign(
          {
            markupType: 'BBBBBB',
            intlValue: 1,
            flatRateValue: 1,
            opexValue: 1,
          },
          new EcomStoreMarkup()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomStoreMarkup', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            markupType: 'BBBBBB',
            minValue: 1,
            domesticValue: 1,
            intlValue: 1,
            flatRateValue: 1,
            opexValue: 1,
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

      it('should delete a EcomStoreMarkup', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomStoreMarkupToCollectionIfMissing', () => {
        it('should add a EcomStoreMarkup to an empty array', () => {
          const ecomStoreMarkup: IEcomStoreMarkup = { id: 123 };
          expectedResult = service.addEcomStoreMarkupToCollectionIfMissing([], ecomStoreMarkup);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStoreMarkup);
        });

        it('should not add a EcomStoreMarkup to an array that contains it', () => {
          const ecomStoreMarkup: IEcomStoreMarkup = { id: 123 };
          const ecomStoreMarkupCollection: IEcomStoreMarkup[] = [
            {
              ...ecomStoreMarkup,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomStoreMarkupToCollectionIfMissing(ecomStoreMarkupCollection, ecomStoreMarkup);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomStoreMarkup to an array that doesn't contain it", () => {
          const ecomStoreMarkup: IEcomStoreMarkup = { id: 123 };
          const ecomStoreMarkupCollection: IEcomStoreMarkup[] = [{ id: 456 }];
          expectedResult = service.addEcomStoreMarkupToCollectionIfMissing(ecomStoreMarkupCollection, ecomStoreMarkup);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStoreMarkup);
        });

        it('should add only unique EcomStoreMarkup to an array', () => {
          const ecomStoreMarkupArray: IEcomStoreMarkup[] = [{ id: 123 }, { id: 456 }, { id: 15788 }];
          const ecomStoreMarkupCollection: IEcomStoreMarkup[] = [{ id: 123 }];
          expectedResult = service.addEcomStoreMarkupToCollectionIfMissing(ecomStoreMarkupCollection, ...ecomStoreMarkupArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomStoreMarkup: IEcomStoreMarkup = { id: 123 };
          const ecomStoreMarkup2: IEcomStoreMarkup = { id: 456 };
          expectedResult = service.addEcomStoreMarkupToCollectionIfMissing([], ecomStoreMarkup, ecomStoreMarkup2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStoreMarkup);
          expect(expectedResult).toContain(ecomStoreMarkup2);
        });

        it('should accept null and undefined values', () => {
          const ecomStoreMarkup: IEcomStoreMarkup = { id: 123 };
          expectedResult = service.addEcomStoreMarkupToCollectionIfMissing([], null, ecomStoreMarkup, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStoreMarkup);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
