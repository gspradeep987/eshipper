import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EcomStoreShipmentSettingsService } from 'app/entities/ecom-store-shipment-settings/ecom-store-shipment-settings.service';
import { IEcomStoreShipmentSettings, EcomStoreShipmentSettings } from 'app/shared/model/ecom-store-shipment-settings.model';

describe('Service Tests', () => {
  describe('EcomStoreShipmentSettings Service', () => {
    let injector: TestBed;
    let service: EcomStoreShipmentSettingsService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomStoreShipmentSettings;
    let expectedResult: IEcomStoreShipmentSettings | IEcomStoreShipmentSettings[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EcomStoreShipmentSettingsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EcomStoreShipmentSettings(0, 'AAAAAAA', 'AAAAAAA', false, false, false, false, false);
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
            id: 0
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
            shippingMode: 'BBBBBB',
            signatureReqd: 'BBBBBB',
            schedulePickup: true,
            liveRates: true,
            addResidential: true,
            tailgateAtDestination: true,
            tailgateAtSource: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomStoreShipmentSettings', () => {
        const returnedFromService = Object.assign(
          {
            shippingMode: 'BBBBBB',
            signatureReqd: 'BBBBBB',
            schedulePickup: true,
            liveRates: true,
            addResidential: true,
            tailgateAtDestination: true,
            tailgateAtSource: true
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
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
