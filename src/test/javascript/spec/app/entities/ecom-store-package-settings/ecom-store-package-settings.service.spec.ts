import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EcomStorePackageSettingsService } from 'app/entities/ecom-store-package-settings/ecom-store-package-settings.service';
import { IEcomStorePackageSettings, EcomStorePackageSettings } from 'app/shared/model/ecom-store-package-settings.model';

describe('Service Tests', () => {
  describe('EcomStorePackageSettings Service', () => {
    let injector: TestBed;
    let service: EcomStorePackageSettingsService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomStorePackageSettings;
    let expectedResult: IEcomStorePackageSettings | IEcomStorePackageSettings[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EcomStorePackageSettingsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EcomStorePackageSettings(0, false, false, false, false);
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
            id: 0
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
            packingInfo1: true,
            packingInfo2: true,
            packingInfo3: true,
            packingInfo4: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomStorePackageSettings', () => {
        const returnedFromService = Object.assign(
          {
            packingInfo1: true,
            packingInfo2: true,
            packingInfo3: true,
            packingInfo4: true
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
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
