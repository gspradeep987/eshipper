import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { WoSalesOperationalDetailsService } from 'app/entities/wo-sales-operational-details/wo-sales-operational-details.service';
import { IWoSalesOperationalDetails, WoSalesOperationalDetails } from 'app/shared/model/wo-sales-operational-details.model';

describe('Service Tests', () => {
  describe('WoSalesOperationalDetails Service', () => {
    let injector: TestBed;
    let service: WoSalesOperationalDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IWoSalesOperationalDetails;
    let expectedResult: IWoSalesOperationalDetails | IWoSalesOperationalDetails[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WoSalesOperationalDetailsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new WoSalesOperationalDetails(0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a WoSalesOperationalDetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new WoSalesOperationalDetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WoSalesOperationalDetails', () => {
        const returnedFromService = Object.assign(
          {
            defaultOpExpense: 1,
            opExpPalletShip: 1,
            opExpPackageShip: 1,
            opExpPack: 1,
            opExpSmartePost: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of WoSalesOperationalDetails', () => {
        const returnedFromService = Object.assign(
          {
            defaultOpExpense: 1,
            opExpPalletShip: 1,
            opExpPackageShip: 1,
            opExpPack: 1,
            opExpSmartePost: 1
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

      it('should delete a WoSalesOperationalDetails', () => {
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
