import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { WoSalesCommissionTransferService } from 'app/entities/wo-sales-commission-transfer/wo-sales-commission-transfer.service';
import { IWoSalesCommissionTransfer, WoSalesCommissionTransfer } from 'app/shared/model/wo-sales-commission-transfer.model';

describe('Service Tests', () => {
  describe('WoSalesCommissionTransfer Service', () => {
    let injector: TestBed;
    let service: WoSalesCommissionTransferService;
    let httpMock: HttpTestingController;
    let elemDefault: IWoSalesCommissionTransfer;
    let expectedResult: IWoSalesCommissionTransfer | IWoSalesCommissionTransfer[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WoSalesCommissionTransferService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new WoSalesCommissionTransfer(0, currentDate, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            customerTransferDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a WoSalesCommissionTransfer', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            customerTransferDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            customerTransferDate: currentDate
          },
          returnedFromService
        );

        service.create(new WoSalesCommissionTransfer()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WoSalesCommissionTransfer', () => {
        const returnedFromService = Object.assign(
          {
            customerTransferDate: currentDate.format(DATE_FORMAT),
            isIncludeHistoricalData: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            customerTransferDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of WoSalesCommissionTransfer', () => {
        const returnedFromService = Object.assign(
          {
            customerTransferDate: currentDate.format(DATE_FORMAT),
            isIncludeHistoricalData: true
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            customerTransferDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a WoSalesCommissionTransfer', () => {
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
