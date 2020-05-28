import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { WoSalesCommissionCarrierService } from 'app/entities/wo-sales-commission-carrier/wo-sales-commission-carrier.service';
import { IWoSalesCommissionCarrier, WoSalesCommissionCarrier } from 'app/shared/model/wo-sales-commission-carrier.model';

describe('Service Tests', () => {
  describe('WoSalesCommissionCarrier Service', () => {
    let injector: TestBed;
    let service: WoSalesCommissionCarrierService;
    let httpMock: HttpTestingController;
    let elemDefault: IWoSalesCommissionCarrier;
    let expectedResult: IWoSalesCommissionCarrier | IWoSalesCommissionCarrier[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WoSalesCommissionCarrierService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new WoSalesCommissionCarrier(0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a WoSalesCommissionCarrier', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new WoSalesCommissionCarrier()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WoSalesCommissionCarrier', () => {
        const returnedFromService = Object.assign(
          {
            commissionPercentageByCarrier: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of WoSalesCommissionCarrier', () => {
        const returnedFromService = Object.assign(
          {
            commissionPercentageByCarrier: 1,
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

      it('should delete a WoSalesCommissionCarrier', () => {
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
