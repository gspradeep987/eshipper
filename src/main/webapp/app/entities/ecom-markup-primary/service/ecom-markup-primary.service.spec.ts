import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomMarkupPrimary, EcomMarkupPrimary } from '../ecom-markup-primary.model';

import { EcomMarkupPrimaryService } from './ecom-markup-primary.service';

describe('Service Tests', () => {
  describe('EcomMarkupPrimary Service', () => {
    let service: EcomMarkupPrimaryService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomMarkupPrimary;
    let expectedResult: IEcomMarkupPrimary | IEcomMarkupPrimary[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomMarkupPrimaryService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        value: 0,
        fromLane: 'AAAAAAA',
        toLane: 'AAAAAAA',
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

      it('should create a EcomMarkupPrimary', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomMarkupPrimary()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomMarkupPrimary', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            value: 1,
            fromLane: 'BBBBBB',
            toLane: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomMarkupPrimary', () => {
        const patchObject = Object.assign(
          {
            value: 1,
            toLane: 'BBBBBB',
          },
          new EcomMarkupPrimary()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomMarkupPrimary', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            value: 1,
            fromLane: 'BBBBBB',
            toLane: 'BBBBBB',
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

      it('should delete a EcomMarkupPrimary', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomMarkupPrimaryToCollectionIfMissing', () => {
        it('should add a EcomMarkupPrimary to an empty array', () => {
          const ecomMarkupPrimary: IEcomMarkupPrimary = { id: 123 };
          expectedResult = service.addEcomMarkupPrimaryToCollectionIfMissing([], ecomMarkupPrimary);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomMarkupPrimary);
        });

        it('should not add a EcomMarkupPrimary to an array that contains it', () => {
          const ecomMarkupPrimary: IEcomMarkupPrimary = { id: 123 };
          const ecomMarkupPrimaryCollection: IEcomMarkupPrimary[] = [
            {
              ...ecomMarkupPrimary,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomMarkupPrimaryToCollectionIfMissing(ecomMarkupPrimaryCollection, ecomMarkupPrimary);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomMarkupPrimary to an array that doesn't contain it", () => {
          const ecomMarkupPrimary: IEcomMarkupPrimary = { id: 123 };
          const ecomMarkupPrimaryCollection: IEcomMarkupPrimary[] = [{ id: 456 }];
          expectedResult = service.addEcomMarkupPrimaryToCollectionIfMissing(ecomMarkupPrimaryCollection, ecomMarkupPrimary);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomMarkupPrimary);
        });

        it('should add only unique EcomMarkupPrimary to an array', () => {
          const ecomMarkupPrimaryArray: IEcomMarkupPrimary[] = [{ id: 123 }, { id: 456 }, { id: 43260 }];
          const ecomMarkupPrimaryCollection: IEcomMarkupPrimary[] = [{ id: 123 }];
          expectedResult = service.addEcomMarkupPrimaryToCollectionIfMissing(ecomMarkupPrimaryCollection, ...ecomMarkupPrimaryArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomMarkupPrimary: IEcomMarkupPrimary = { id: 123 };
          const ecomMarkupPrimary2: IEcomMarkupPrimary = { id: 456 };
          expectedResult = service.addEcomMarkupPrimaryToCollectionIfMissing([], ecomMarkupPrimary, ecomMarkupPrimary2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomMarkupPrimary);
          expect(expectedResult).toContain(ecomMarkupPrimary2);
        });

        it('should accept null and undefined values', () => {
          const ecomMarkupPrimary: IEcomMarkupPrimary = { id: 123 };
          expectedResult = service.addEcomMarkupPrimaryToCollectionIfMissing([], null, ecomMarkupPrimary, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomMarkupPrimary);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
