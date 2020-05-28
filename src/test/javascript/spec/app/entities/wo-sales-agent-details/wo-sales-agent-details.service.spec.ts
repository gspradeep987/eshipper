import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { WoSalesAgentDetailsService } from 'app/entities/wo-sales-agent-details/wo-sales-agent-details.service';
import { IWoSalesAgentDetails, WoSalesAgentDetails } from 'app/shared/model/wo-sales-agent-details.model';

describe('Service Tests', () => {
  describe('WoSalesAgentDetails Service', () => {
    let injector: TestBed;
    let service: WoSalesAgentDetailsService;
    let httpMock: HttpTestingController;
    let elemDefault: IWoSalesAgentDetails;
    let expectedResult: IWoSalesAgentDetails | IWoSalesAgentDetails[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WoSalesAgentDetailsService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new WoSalesAgentDetails(0, 0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a WoSalesAgentDetails', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new WoSalesAgentDetails()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WoSalesAgentDetails', () => {
        const returnedFromService = Object.assign(
          {
            hstNumber: 1,
            promoCode: 'BBBBBB',
            promoUrl: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of WoSalesAgentDetails', () => {
        const returnedFromService = Object.assign(
          {
            hstNumber: 1,
            promoCode: 'BBBBBB',
            promoUrl: 'BBBBBB',
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

      it('should delete a WoSalesAgentDetails', () => {
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
