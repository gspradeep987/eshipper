import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { EcomAutomationRulesService } from 'app/entities/ecom-automation-rules/ecom-automation-rules.service';
import { IEcomAutomationRules, EcomAutomationRules } from 'app/shared/model/ecom-automation-rules.model';

describe('Service Tests', () => {
  describe('EcomAutomationRules Service', () => {
    let injector: TestBed;
    let service: EcomAutomationRulesService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomAutomationRules;
    let expectedResult: IEcomAutomationRules | IEcomAutomationRules[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EcomAutomationRulesService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new EcomAutomationRules(0, 'AAAAAAA', false, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            createdDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EcomAutomationRules', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            createdDate: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
          },
          returnedFromService
        );

        service.create(new EcomAutomationRules()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomAutomationRules', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            enable: true,
            createdDate: currentDate.format(DATE_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomAutomationRules', () => {
        const returnedFromService = Object.assign(
          {
            name: 'BBBBBB',
            enable: true,
            createdDate: currentDate.format(DATE_FORMAT),
            createdBy: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            createdDate: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a EcomAutomationRules', () => {
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
