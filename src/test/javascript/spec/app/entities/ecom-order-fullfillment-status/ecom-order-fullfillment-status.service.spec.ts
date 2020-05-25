import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EcomOrderFullfillmentStatusService } from 'app/entities/ecom-order-fullfillment-status/ecom-order-fullfillment-status.service';
import { IEcomOrderFullfillmentStatus, EcomOrderFullfillmentStatus } from 'app/shared/model/ecom-order-fullfillment-status.model';

describe('Service Tests', () => {
  describe('EcomOrderFullfillmentStatus Service', () => {
    let injector: TestBed;
    let service: EcomOrderFullfillmentStatusService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomOrderFullfillmentStatus;
    let expectedResult: IEcomOrderFullfillmentStatus | IEcomOrderFullfillmentStatus[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EcomOrderFullfillmentStatusService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EcomOrderFullfillmentStatus(0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EcomOrderFullfillmentStatus', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomOrderFullfillmentStatus()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomOrderFullfillmentStatus', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            value: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomOrderFullfillmentStatus', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            value: 'BBBBBB',
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

      it('should delete a EcomOrderFullfillmentStatus', () => {
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
