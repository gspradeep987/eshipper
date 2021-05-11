import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomStoreShipmentSettings, EcomStoreShipmentSettings } from '../ecom-store-shipment-settings.model';

import { EcomStoreShipmentSettingsService } from './ecom-store-shipment-settings.service';

describe('Service Tests', () => {
  describe('EcomStoreShipmentSettings Service', () => {
    let service: EcomStoreShipmentSettingsService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomStoreShipmentSettings;
    let expectedResult: IEcomStoreShipmentSettings | IEcomStoreShipmentSettings[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomStoreShipmentSettingsService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        shippingMode: 'AAAAAAA',
        signatureReqd: 'AAAAAAA',
        schedulePickup: false,
        liveRates: false,
        addResidential: false,
        tailgateAtDestination: false,
        tailgateAtSource: false,
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

      it('should create a EcomStoreShipmentSettings', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomStoreShipmentSettings()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomStoreShipmentSettings', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            shippingMode: 'BBBBBB',
            signatureReqd: 'BBBBBB',
            schedulePickup: true,
            liveRates: true,
            addResidential: true,
            tailgateAtDestination: true,
            tailgateAtSource: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomStoreShipmentSettings', () => {
        const patchObject = Object.assign(
          {
            signatureReqd: 'BBBBBB',
            tailgateAtSource: true,
          },
          new EcomStoreShipmentSettings()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomStoreShipmentSettings', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            shippingMode: 'BBBBBB',
            signatureReqd: 'BBBBBB',
            schedulePickup: true,
            liveRates: true,
            addResidential: true,
            tailgateAtDestination: true,
            tailgateAtSource: true,
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

      it('should delete a EcomStoreShipmentSettings', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomStoreShipmentSettingsToCollectionIfMissing', () => {
        it('should add a EcomStoreShipmentSettings to an empty array', () => {
          const ecomStoreShipmentSettings: IEcomStoreShipmentSettings = { id: 123 };
          expectedResult = service.addEcomStoreShipmentSettingsToCollectionIfMissing([], ecomStoreShipmentSettings);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStoreShipmentSettings);
        });

        it('should not add a EcomStoreShipmentSettings to an array that contains it', () => {
          const ecomStoreShipmentSettings: IEcomStoreShipmentSettings = { id: 123 };
          const ecomStoreShipmentSettingsCollection: IEcomStoreShipmentSettings[] = [
            {
              ...ecomStoreShipmentSettings,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomStoreShipmentSettingsToCollectionIfMissing(
            ecomStoreShipmentSettingsCollection,
            ecomStoreShipmentSettings
          );
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomStoreShipmentSettings to an array that doesn't contain it", () => {
          const ecomStoreShipmentSettings: IEcomStoreShipmentSettings = { id: 123 };
          const ecomStoreShipmentSettingsCollection: IEcomStoreShipmentSettings[] = [{ id: 456 }];
          expectedResult = service.addEcomStoreShipmentSettingsToCollectionIfMissing(
            ecomStoreShipmentSettingsCollection,
            ecomStoreShipmentSettings
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStoreShipmentSettings);
        });

        it('should add only unique EcomStoreShipmentSettings to an array', () => {
          const ecomStoreShipmentSettingsArray: IEcomStoreShipmentSettings[] = [{ id: 123 }, { id: 456 }, { id: 54898 }];
          const ecomStoreShipmentSettingsCollection: IEcomStoreShipmentSettings[] = [{ id: 123 }];
          expectedResult = service.addEcomStoreShipmentSettingsToCollectionIfMissing(
            ecomStoreShipmentSettingsCollection,
            ...ecomStoreShipmentSettingsArray
          );
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomStoreShipmentSettings: IEcomStoreShipmentSettings = { id: 123 };
          const ecomStoreShipmentSettings2: IEcomStoreShipmentSettings = { id: 456 };
          expectedResult = service.addEcomStoreShipmentSettingsToCollectionIfMissing(
            [],
            ecomStoreShipmentSettings,
            ecomStoreShipmentSettings2
          );
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomStoreShipmentSettings);
          expect(expectedResult).toContain(ecomStoreShipmentSettings2);
        });

        it('should accept null and undefined values', () => {
          const ecomStoreShipmentSettings: IEcomStoreShipmentSettings = { id: 123 };
          expectedResult = service.addEcomStoreShipmentSettingsToCollectionIfMissing([], null, ecomStoreShipmentSettings, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomStoreShipmentSettings);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
