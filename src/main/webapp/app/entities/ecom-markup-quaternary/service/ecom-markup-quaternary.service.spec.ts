import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomMarkupQuaternary, EcomMarkupQuaternary } from '../ecom-markup-quaternary.model';

import { EcomMarkupQuaternaryService } from './ecom-markup-quaternary.service';

describe('Service Tests', () => {
  describe('EcomMarkupQuaternary Service', () => {
    let service: EcomMarkupQuaternaryService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomMarkupQuaternary;
    let expectedResult: IEcomMarkupQuaternary | IEcomMarkupQuaternary[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomMarkupQuaternaryService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        value: 0,
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

      it('should create a EcomMarkupQuaternary', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomMarkupQuaternary()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomMarkupQuaternary', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            value: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomMarkupQuaternary', () => {
        const patchObject = Object.assign({}, new EcomMarkupQuaternary());

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomMarkupQuaternary', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            value: 1,
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

      it('should delete a EcomMarkupQuaternary', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomMarkupQuaternaryToCollectionIfMissing', () => {
        it('should add a EcomMarkupQuaternary to an empty array', () => {
          const ecomMarkupQuaternary: IEcomMarkupQuaternary = { id: 123 };
          expectedResult = service.addEcomMarkupQuaternaryToCollectionIfMissing([], ecomMarkupQuaternary);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomMarkupQuaternary);
        });

        it('should not add a EcomMarkupQuaternary to an array that contains it', () => {
          const ecomMarkupQuaternary: IEcomMarkupQuaternary = { id: 123 };
          const ecomMarkupQuaternaryCollection: IEcomMarkupQuaternary[] = [
            {
              ...ecomMarkupQuaternary,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomMarkupQuaternaryToCollectionIfMissing(ecomMarkupQuaternaryCollection, ecomMarkupQuaternary);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomMarkupQuaternary to an array that doesn't contain it", () => {
          const ecomMarkupQuaternary: IEcomMarkupQuaternary = { id: 123 };
          const ecomMarkupQuaternaryCollection: IEcomMarkupQuaternary[] = [{ id: 456 }];
          expectedResult = service.addEcomMarkupQuaternaryToCollectionIfMissing(ecomMarkupQuaternaryCollection, ecomMarkupQuaternary);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomMarkupQuaternary);
        });

        it('should add only unique EcomMarkupQuaternary to an array', () => {
          const ecomMarkupQuaternaryArray: IEcomMarkupQuaternary[] = [{ id: 123 }, { id: 456 }, { id: 94429 }];
          const ecomMarkupQuaternaryCollection: IEcomMarkupQuaternary[] = [{ id: 123 }];
          expectedResult = service.addEcomMarkupQuaternaryToCollectionIfMissing(
            ecomMarkupQuaternaryCollection,
            ...ecomMarkupQuaternaryArray
          );
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomMarkupQuaternary: IEcomMarkupQuaternary = { id: 123 };
          const ecomMarkupQuaternary2: IEcomMarkupQuaternary = { id: 456 };
          expectedResult = service.addEcomMarkupQuaternaryToCollectionIfMissing([], ecomMarkupQuaternary, ecomMarkupQuaternary2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomMarkupQuaternary);
          expect(expectedResult).toContain(ecomMarkupQuaternary2);
        });

        it('should accept null and undefined values', () => {
          const ecomMarkupQuaternary: IEcomMarkupQuaternary = { id: 123 };
          expectedResult = service.addEcomMarkupQuaternaryToCollectionIfMissing([], null, ecomMarkupQuaternary, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomMarkupQuaternary);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
