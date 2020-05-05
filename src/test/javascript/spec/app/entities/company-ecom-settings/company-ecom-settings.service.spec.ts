import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CompanyEcomSettingsService } from 'app/entities/company-ecom-settings/company-ecom-settings.service';
import { ICompanyEcomSettings, CompanyEcomSettings } from 'app/shared/model/company-ecom-settings.model';

describe('Service Tests', () => {
  describe('CompanyEcomSettings Service', () => {
    let injector: TestBed;
    let service: CompanyEcomSettingsService;
    let httpMock: HttpTestingController;
    let elemDefault: ICompanyEcomSettings;
    let expectedResult: ICompanyEcomSettings | ICompanyEcomSettings[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CompanyEcomSettingsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CompanyEcomSettings(0, false, false, false, false, false, false, false, false, false, false, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CompanyEcomSettings', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CompanyEcomSettings()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CompanyEcomSettings', () => {
        const returnedFromService = Object.assign(
          {
            notifyRecipient: true,
            createPackingList: true,
            createPackingSlip: true,
            ecomModule: true,
            includeTaxesInRvenues: true,
            showAvgCustomerValue: true,
            showAvgOrderValue: true,
            showAvgShipmentValue: true,
            removeSerialReturners: true,
            showPackageStatistics: true,
            includeAllItemsRetCustomers: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CompanyEcomSettings', () => {
        const returnedFromService = Object.assign(
          {
            notifyRecipient: true,
            createPackingList: true,
            createPackingSlip: true,
            ecomModule: true,
            includeTaxesInRvenues: true,
            showAvgCustomerValue: true,
            showAvgOrderValue: true,
            showAvgShipmentValue: true,
            removeSerialReturners: true,
            showPackageStatistics: true,
            includeAllItemsRetCustomers: true
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

      it('should delete a CompanyEcomSettings', () => {
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
