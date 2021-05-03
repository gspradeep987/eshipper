import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as dayjs from 'dayjs';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IEcomOrder, EcomOrder } from '../ecom-order.model';

import { EcomOrderService } from './ecom-order.service';

describe('Service Tests', () => {
  describe('EcomOrder Service', () => {
    let service: EcomOrderService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomOrder;
    let expectedResult: IEcomOrder | IEcomOrder[] | boolean | null;
    let currentDate: dayjs.Dayjs;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomOrderService);
      httpMock = TestBed.inject(HttpTestingController);
      currentDate = dayjs();

      elemDefault = {
        id: 0,
        ecomOrderNumber: 0,
        customerName: 'AAAAAAA',
        domainName: 'AAAAAAA',
        name: 'AAAAAAA',
        email: 'AAAAAAA',
        gateway: 'AAAAAAA',
        totalPrice: 0,
        subTotalPrice: 0,
        totalWeight: 0,
        totalTax: 0,
        fulfillmentStatus: 'AAAAAAA',
        paymentStatus: 'AAAAAAA',
        financialStatus: 'AAAAAAA',
        createdDate: currentDate,
        updatedDate: currentDate,
        updatedBy: 'AAAAAAA',
        isCancelled: false,
        isShipped: false,
        eshipperStatus: 'AAAAAAA',
        residentialShippingAddress: false,
        shippingOrderRef: 0,
        fromEmail: 'AAAAAAA',
        isCancelSchedule: false,
        isSchedulePickup: false,
        packageTypeId: 0,
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdDate: currentDate.format(DATE_FORMAT),
            updatedDate: currentDate.format(DATE_FORMAT),
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
            updatedDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            updatedDate: currentDate,
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
            id: 1,
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
            packageTypeId: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            updatedDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomOrder', () => {
        const patchObject = Object.assign(
          {
            ecomOrderNumber: 1,
            customerName: 'BBBBBB',
            name: 'BBBBBB',
            email: 'BBBBBB',
            gateway: 'BBBBBB',
            subTotalPrice: 1,
            totalTax: 1,
            fulfillmentStatus: 'BBBBBB',
            createdDate: currentDate.format(DATE_FORMAT),
            updatedDate: currentDate.format(DATE_FORMAT),
            updatedBy: 'BBBBBB',
            eshipperStatus: 'BBBBBB',
            isCancelSchedule: true,
            isSchedulePickup: true,
            packageTypeId: 1,
          },
          new EcomOrder()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign(
          {
            createdDate: currentDate,
            updatedDate: currentDate,
          },
          returnedFromService
        );

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomOrder', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
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
            packageTypeId: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
            updatedDate: currentDate,
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

      describe('addEcomOrderToCollectionIfMissing', () => {
        it('should add a EcomOrder to an empty array', () => {
          const ecomOrder: IEcomOrder = { id: 123 };
          expectedResult = service.addEcomOrderToCollectionIfMissing([], ecomOrder);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomOrder);
        });

        it('should not add a EcomOrder to an array that contains it', () => {
          const ecomOrder: IEcomOrder = { id: 123 };
          const ecomOrderCollection: IEcomOrder[] = [
            {
              ...ecomOrder,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomOrderToCollectionIfMissing(ecomOrderCollection, ecomOrder);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomOrder to an array that doesn't contain it", () => {
          const ecomOrder: IEcomOrder = { id: 123 };
          const ecomOrderCollection: IEcomOrder[] = [{ id: 456 }];
          expectedResult = service.addEcomOrderToCollectionIfMissing(ecomOrderCollection, ecomOrder);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomOrder);
        });

        it('should add only unique EcomOrder to an array', () => {
          const ecomOrderArray: IEcomOrder[] = [{ id: 123 }, { id: 456 }, { id: 58651 }];
          const ecomOrderCollection: IEcomOrder[] = [{ id: 123 }];
          expectedResult = service.addEcomOrderToCollectionIfMissing(ecomOrderCollection, ...ecomOrderArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomOrder: IEcomOrder = { id: 123 };
          const ecomOrder2: IEcomOrder = { id: 456 };
          expectedResult = service.addEcomOrderToCollectionIfMissing([], ecomOrder, ecomOrder2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomOrder);
          expect(expectedResult).toContain(ecomOrder2);
        });

        it('should accept null and undefined values', () => {
          const ecomOrder: IEcomOrder = { id: 123 };
          expectedResult = service.addEcomOrderToCollectionIfMissing([], null, ecomOrder, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomOrder);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
