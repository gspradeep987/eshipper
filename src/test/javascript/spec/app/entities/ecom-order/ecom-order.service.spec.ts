import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { EcomOrderService } from 'app/entities/ecom-order/ecom-order.service';
import { IEcomOrder, EcomOrder } from 'app/shared/model/ecom-order.model';

describe('Service Tests', () => {
  describe('EcomOrder Service', () => {
    let injector: TestBed;
    let service: EcomOrderService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomOrder;
    let expectedResult: IEcomOrder | IEcomOrder[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EcomOrderService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new EcomOrder(
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        currentDate,
        'AAAAAAA',
        false,
        false,
        'AAAAAAA',
        false,
        0,
        'AAAAAAA',
        false,
        false,
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdDate: currentDate.format(DATE_FORMAT),
            updatedDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EcomOrder', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdDate: currentDate.format(DATE_FORMAT),
            updatedDate: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            updatedDate: currentDate
          },
          returnedFromService
        );

        service.create(new EcomOrder()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomOrder', () => {
        const returnedFromService = Object.assign(
          {
            ecomOrderNumber: 1,
            customerName: 'BBBBBB',
            domainName: 'BBBBBB',
            name: 'BBBBBB',
            email: 'BBBBBB',
            gateway: 'BBBBBB',
            totalPrice: 1,
            subTotalPrice: 1,
            totalWeight: 1,
            totalTax: 1,
            fulfillmentStatus: 'BBBBBB',
            paymentStatus: 'BBBBBB',
            financialStatus: 'BBBBBB',
            createdDate: currentDate.format(DATE_FORMAT),
            updatedDate: currentDate.format(DATE_FORMAT),
            updatedBy: 'BBBBBB',
            isCancelled: true,
            isShipped: true,
            eshipperStatus: 'BBBBBB',
            residentialShippingAddress: true,
            shippingOrderRef: 1,
            fromEmail: 'BBBBBB',
            isCancelSchedule: true,
            isSchedulePickup: true,
            packageTypeId: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            updatedDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomOrder', () => {
        const returnedFromService = Object.assign(
          {
            ecomOrderNumber: 1,
            customerName: 'BBBBBB',
            domainName: 'BBBBBB',
            name: 'BBBBBB',
            email: 'BBBBBB',
            gateway: 'BBBBBB',
            totalPrice: 1,
            subTotalPrice: 1,
            totalWeight: 1,
            totalTax: 1,
            fulfillmentStatus: 'BBBBBB',
            paymentStatus: 'BBBBBB',
            financialStatus: 'BBBBBB',
            createdDate: currentDate.format(DATE_FORMAT),
            updatedDate: currentDate.format(DATE_FORMAT),
            updatedBy: 'BBBBBB',
            isCancelled: true,
            isShipped: true,
            eshipperStatus: 'BBBBBB',
            residentialShippingAddress: true,
            shippingOrderRef: 1,
            fromEmail: 'BBBBBB',
            isCancelSchedule: true,
            isSchedulePickup: true,
            packageTypeId: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            updatedDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a EcomOrder', () => {
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
