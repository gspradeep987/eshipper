import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { WoSalesCommissionDetailsService } from 'app/entities/wo-sales-commission-details/wo-sales-commission-details.service';
import { IWoSalesCommissionDetails, WoSalesCommissionDetails } from 'app/shared/model/wo-sales-commission-details.model';

describe('Service Tests', () => {
  describe('WoSalesCommissionDetails Service', () => {
    let injector: TestBed;
    let service: WoSalesCommissionDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IWoSalesCommissionDetails;
    let expectedResult: IWoSalesCommissionDetails | IWoSalesCommissionDetails[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WoSalesCommissionDetailsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new WoSalesCommissionDetails(0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a WoSalesCommissionDetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new WoSalesCommissionDetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WoSalesCommissionDetails', () => {
        const returnedFromService = Object.assign(
          {
            commission: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of WoSalesCommissionDetails', () => {
        const returnedFromService = Object.assign(
          {
            commission: 1,
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

      it('should delete a WoSalesCommissionDetails', () => {
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
