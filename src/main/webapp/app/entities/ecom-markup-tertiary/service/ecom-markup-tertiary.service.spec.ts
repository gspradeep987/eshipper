import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomMarkupTertiary, EcomMarkupTertiary } from '../ecom-markup-tertiary.model';

import { EcomMarkupTertiaryService } from './ecom-markup-tertiary.service';

describe('Service Tests', () => {
  describe('EcomMarkupTertiary Service', () => {
    let service: EcomMarkupTertiaryService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomMarkupTertiary;
    let expectedResult: IEcomMarkupTertiary | IEcomMarkupTertiary[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomMarkupTertiaryService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        wgt1to5: 0,
        wgt6to10: 0,
        wgt11to15: 0,
        wgt16: 0,
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

      it('should create a EcomMarkupTertiary', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomMarkupTertiary()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomMarkupTertiary', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            wgt1to5: 1,
            wgt6to10: 1,
            wgt11to15: 1,
            wgt16: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomMarkupTertiary', () => {
        const patchObject = Object.assign(
          {
            wgt1to5: 1,
          },
          new EcomMarkupTertiary()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomMarkupTertiary', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            wgt1to5: 1,
            wgt6to10: 1,
            wgt11to15: 1,
            wgt16: 1,
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

      it('should delete a EcomMarkupTertiary', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomMarkupTertiaryToCollectionIfMissing', () => {
        it('should add a EcomMarkupTertiary to an empty array', () => {
          const ecomMarkupTertiary: IEcomMarkupTertiary = { id: 123 };
          expectedResult = service.addEcomMarkupTertiaryToCollectionIfMissing([], ecomMarkupTertiary);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomMarkupTertiary);
        });

        it('should not add a EcomMarkupTertiary to an array that contains it', () => {
          const ecomMarkupTertiary: IEcomMarkupTertiary = { id: 123 };
          const ecomMarkupTertiaryCollection: IEcomMarkupTertiary[] = [
            {
              ...ecomMarkupTertiary,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomMarkupTertiaryToCollectionIfMissing(ecomMarkupTertiaryCollection, ecomMarkupTertiary);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomMarkupTertiary to an array that doesn't contain it", () => {
          const ecomMarkupTertiary: IEcomMarkupTertiary = { id: 123 };
          const ecomMarkupTertiaryCollection: IEcomMarkupTertiary[] = [{ id: 456 }];
          expectedResult = service.addEcomMarkupTertiaryToCollectionIfMissing(ecomMarkupTertiaryCollection, ecomMarkupTertiary);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomMarkupTertiary);
        });

        it('should add only unique EcomMarkupTertiary to an array', () => {
          const ecomMarkupTertiaryArray: IEcomMarkupTertiary[] = [{ id: 123 }, { id: 456 }, { id: 69988 }];
          const ecomMarkupTertiaryCollection: IEcomMarkupTertiary[] = [{ id: 123 }];
          expectedResult = service.addEcomMarkupTertiaryToCollectionIfMissing(ecomMarkupTertiaryCollection, ...ecomMarkupTertiaryArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomMarkupTertiary: IEcomMarkupTertiary = { id: 123 };
          const ecomMarkupTertiary2: IEcomMarkupTertiary = { id: 456 };
          expectedResult = service.addEcomMarkupTertiaryToCollectionIfMissing([], ecomMarkupTertiary, ecomMarkupTertiary2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomMarkupTertiary);
          expect(expectedResult).toContain(ecomMarkupTertiary2);
        });

        it('should accept null and undefined values', () => {
          const ecomMarkupTertiary: IEcomMarkupTertiary = { id: 123 };
          expectedResult = service.addEcomMarkupTertiaryToCollectionIfMissing([], null, ecomMarkupTertiary, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomMarkupTertiary);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
