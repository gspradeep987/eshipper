import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomStoreColorTheme, EcomStoreColorTheme } from '../ecom-store-color-theme.model';

import { EcomStoreColorThemeService } from './ecom-store-color-theme.service';

describe('Service Tests', () => {
  describe('EcomStoreColorTheme Service', () => {
    let service: EcomStoreColorThemeService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomStoreColorTheme;
    let expectedResult: IEcomStoreColorTheme | IEcomStoreColorTheme[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomStoreColorThemeService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        primary: 'AAAAAAA',
        secondary: 'AAAAAAA',
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

      it('should create a EcomStoreColorTheme', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomStoreColorTheme()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomStoreColorTheme', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            primary: 'BBBBBB',
            secondary: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomStoreColorTheme', () => {
        const patchObject = Object.assign(
          {
            primary: 'BBBBBB',
            secondary: 'BBBBBB',
          },
          new EcomStoreColorTheme()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomStoreColorTheme', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            primary: 'BBBBBB',
            secondary: 'BBBBBB',
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

      it('should delete a EcomStoreColorTheme', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomStoreColorThemeToCollectionIfMissing', () => {
        it('should add a EcomStoreColorTheme to an empty array', () => {
          const ecomStoreColorTheme: IEcomStoreColorTheme = { id: 123 };
          expectedResult = service.addEcomStoreColorThemeToCollectionIfMissing([], ecomStoreColorTheme);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStoreColorTheme);
        });

        it('should not add a EcomStoreColorTheme to an array that contains it', () => {
          const ecomStoreColorTheme: IEcomStoreColorTheme = { id: 123 };
          const ecomStoreColorThemeCollection: IEcomStoreColorTheme[] = [
            {
              ...ecomStoreColorTheme,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomStoreColorThemeToCollectionIfMissing(ecomStoreColorThemeCollection, ecomStoreColorTheme);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomStoreColorTheme to an array that doesn't contain it", () => {
          const ecomStoreColorTheme: IEcomStoreColorTheme = { id: 123 };
          const ecomStoreColorThemeCollection: IEcomStoreColorTheme[] = [{ id: 456 }];
          expectedResult = service.addEcomStoreColorThemeToCollectionIfMissing(ecomStoreColorThemeCollection, ecomStoreColorTheme);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStoreColorTheme);
        });

        it('should add only unique EcomStoreColorTheme to an array', () => {
          const ecomStoreColorThemeArray: IEcomStoreColorTheme[] = [{ id: 123 }, { id: 456 }, { id: 41098 }];
          const ecomStoreColorThemeCollection: IEcomStoreColorTheme[] = [{ id: 123 }];
          expectedResult = service.addEcomStoreColorThemeToCollectionIfMissing(ecomStoreColorThemeCollection, ...ecomStoreColorThemeArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomStoreColorTheme: IEcomStoreColorTheme = { id: 123 };
          const ecomStoreColorTheme2: IEcomStoreColorTheme = { id: 456 };
          expectedResult = service.addEcomStoreColorThemeToCollectionIfMissing([], ecomStoreColorTheme, ecomStoreColorTheme2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStoreColorTheme);
          expect(expectedResult).toContain(ecomStoreColorTheme2);
        });

        it('should accept null and undefined values', () => {
          const ecomStoreColorTheme: IEcomStoreColorTheme = { id: 123 };
          expectedResult = service.addEcomStoreColorThemeToCollectionIfMissing([], null, ecomStoreColorTheme, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStoreColorTheme);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
