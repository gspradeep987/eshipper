import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomStorePackageSettings, EcomStorePackageSettings } from '../ecom-store-package-settings.model';

import { EcomStorePackageSettingsService } from './ecom-store-package-settings.service';

describe('Service Tests', () => {
  describe('EcomStorePackageSettings Service', () => {
    let service: EcomStorePackageSettingsService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomStorePackageSettings;
    let expectedResult: IEcomStorePackageSettings | IEcomStorePackageSettings[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomStorePackageSettingsService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        packingInfo1: false,
        packingInfo2: false,
        packingInfo3: false,
        packingInfo4: false,
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

      it('should create a EcomStorePackageSettings', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomStorePackageSettings()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomStorePackageSettings', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            packingInfo1: true,
            packingInfo2: true,
            packingInfo3: true,
            packingInfo4: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomStorePackageSettings', () => {
        const patchObject = Object.assign(
          {
            packingInfo1: true,
            packingInfo3: true,
          },
          new EcomStorePackageSettings()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomStorePackageSettings', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            packingInfo1: true,
            packingInfo2: true,
            packingInfo3: true,
            packingInfo4: true,
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

      it('should delete a EcomStorePackageSettings', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomStorePackageSettingsToCollectionIfMissing', () => {
        it('should add a EcomStorePackageSettings to an empty array', () => {
          const ecomStorePackageSettings: IEcomStorePackageSettings = { id: 123 };
          expectedResult = service.addEcomStorePackageSettingsToCollectionIfMissing([], ecomStorePackageSettings);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStorePackageSettings);
        });

        it('should not add a EcomStorePackageSettings to an array that contains it', () => {
          const ecomStorePackageSettings: IEcomStorePackageSettings = { id: 123 };
          const ecomStorePackageSettingsCollection: IEcomStorePackageSettings[] = [
            {
              ...ecomStorePackageSettings,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomStorePackageSettingsToCollectionIfMissing(
            ecomStorePackageSettingsCollection,
            ecomStorePackageSettings
          );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomStorePackageSettings to an array that doesn't contain it", () => {
          const ecomStorePackageSettings: IEcomStorePackageSettings = { id: 123 };
          const ecomStorePackageSettingsCollection: IEcomStorePackageSettings[] = [{ id: 456 }];
          expectedResult = service.addEcomStorePackageSettingsToCollectionIfMissing(
            ecomStorePackageSettingsCollection,
            ecomStorePackageSettings
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStorePackageSettings);
        });

        it('should add only unique EcomStorePackageSettings to an array', () => {
          const ecomStorePackageSettingsArray: IEcomStorePackageSettings[] = [{ id: 123 }, { id: 456 }, { id: 95803 }];
          const ecomStorePackageSettingsCollection: IEcomStorePackageSettings[] = [{ id: 123 }];
          expectedResult = service.addEcomStorePackageSettingsToCollectionIfMissing(
            ecomStorePackageSettingsCollection,
            ...ecomStorePackageSettingsArray
          );
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomStorePackageSettings: IEcomStorePackageSettings = { id: 123 };
          const ecomStorePackageSettings2: IEcomStorePackageSettings = { id: 456 };
          expectedResult = service.addEcomStorePackageSettingsToCollectionIfMissing(
            [],
            ecomStorePackageSettings,
            ecomStorePackageSettings2
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStorePackageSettings);
          expect(expectedResult).toContain(ecomStorePackageSettings2);
        });

        it('should accept null and undefined values', () => {
          const ecomStorePackageSettings: IEcomStorePackageSettings = { id: 123 };
          expectedResult = service.addEcomStorePackageSettingsToCollectionIfMissing([], null, ecomStorePackageSettings, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStorePackageSettings);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
