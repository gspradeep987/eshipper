import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CompanyCarrierAccountService } from 'app/entities/company-carrier-account/company-carrier-account.service';
import { ICompanyCarrierAccount, CompanyCarrierAccount } from 'app/shared/model/company-carrier-account.model';
import { AccountOwner } from 'app/shared/model/enumerations/account-owner.model';

describe('Service Tests', () => {
  describe('CompanyCarrierAccount Service', () => {
    let injector: TestBed;
    let service: CompanyCarrierAccountService;
    let httpMock: HttpTestingController;
    let elemDefault: ICompanyCarrierAccount;
    let expectedResult: ICompanyCarrierAccount | ICompanyCarrierAccount[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CompanyCarrierAccountService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CompanyCarrierAccount(0, AccountOwner.OWNACCOUNT, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CompanyCarrierAccount', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CompanyCarrierAccount()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CompanyCarrierAccount', () => {
        const returnedFromService = Object.assign(
          {
            accountOwner: 'BBBBBB',
            accountNumber: 1,
            meterNumber: 'BBBBBB',
            key: 'BBBBBB',
            password: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CompanyCarrierAccount', () => {
        const returnedFromService = Object.assign(
          {
            accountOwner: 'BBBBBB',
            accountNumber: 1,
            meterNumber: 'BBBBBB',
            key: 'BBBBBB',
            password: 'BBBBBB'
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

      it('should delete a CompanyCarrierAccount', () => {
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
