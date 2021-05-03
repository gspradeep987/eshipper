import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomMarkupSecondary, EcomMarkupSecondary } from '../ecom-markup-secondary.model';

import { EcomMarkupSecondaryService } from './ecom-markup-secondary.service';

describe('Service Tests', () => {
  describe('EcomMarkupSecondary Service', () => {
    let service: EcomMarkupSecondaryService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomMarkupSecondary;
    let expectedResult: IEcomMarkupSecondary | IEcomMarkupSecondary[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomMarkupSecondaryService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        value: 0,
        fromZip: 'AAAAAAA',
        toZip: 'AAAAAAA',
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

      it('should create a EcomMarkupSecondary', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomMarkupSecondary()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomMarkupSecondary', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            value: 1,
            fromZip: 'BBBBBB',
            toZip: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomMarkupSecondary', () => {
        const patchObject = Object.assign(
          {
            fromZip: 'BBBBBB',
          },
          new EcomMarkupSecondary()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomMarkupSecondary', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            value: 1,
            fromZip: 'BBBBBB',
            toZip: 'BBBBBB',
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

      it('should delete a EcomMarkupSecondary', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomMarkupSecondaryToCollectionIfMissing', () => {
        it('should add a EcomMarkupSecondary to an empty array', () => {
          const ecomMarkupSecondary: IEcomMarkupSecondary = { id: 123 };
          expectedResult = service.addEcomMarkupSecondaryToCollectionIfMissing([], ecomMarkupSecondary);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomMarkupSecondary);
        });

        it('should not add a EcomMarkupSecondary to an array that contains it', () => {
          const ecomMarkupSecondary: IEcomMarkupSecondary = { id: 123 };
          const ecomMarkupSecondaryCollection: IEcomMarkupSecondary[] = [
            {
              ...ecomMarkupSecondary,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomMarkupSecondaryToCollectionIfMissing(ecomMarkupSecondaryCollection, ecomMarkupSecondary);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomMarkupSecondary to an array that doesn't contain it", () => {
          const ecomMarkupSecondary: IEcomMarkupSecondary = { id: 123 };
          const ecomMarkupSecondaryCollection: IEcomMarkupSecondary[] = [{ id: 456 }];
          expectedResult = service.addEcomMarkupSecondaryToCollectionIfMissing(ecomMarkupSecondaryCollection, ecomMarkupSecondary);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomMarkupSecondary);
        });

        it('should add only unique EcomMarkupSecondary to an array', () => {
          const ecomMarkupSecondaryArray: IEcomMarkupSecondary[] = [{ id: 123 }, { id: 456 }, { id: 60497 }];
          const ecomMarkupSecondaryCollection: IEcomMarkupSecondary[] = [{ id: 123 }];
          expectedResult = service.addEcomMarkupSecondaryToCollectionIfMissing(ecomMarkupSecondaryCollection, ...ecomMarkupSecondaryArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomMarkupSecondary: IEcomMarkupSecondary = { id: 123 };
          const ecomMarkupSecondary2: IEcomMarkupSecondary = { id: 456 };
          expectedResult = service.addEcomMarkupSecondaryToCollectionIfMissing([], ecomMarkupSecondary, ecomMarkupSecondary2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomMarkupSecondary);
          expect(expectedResult).toContain(ecomMarkupSecondary2);
        });

        it('should accept null and undefined values', () => {
          const ecomMarkupSecondary: IEcomMarkupSecondary = { id: 123 };
          expectedResult = service.addEcomMarkupSecondaryToCollectionIfMissing([], null, ecomMarkupSecondary, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomMarkupSecondary);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
