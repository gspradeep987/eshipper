import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EcomStoreAddressService } from 'app/entities/ecom-store-address/ecom-store-address.service';
import { IEcomStoreAddress, EcomStoreAddress } from 'app/shared/model/ecom-store-address.model';

describe('Service Tests', () => {
  describe('EcomStoreAddress Service', () => {
    let injector: TestBed;
    let service: EcomStoreAddressService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomStoreAddress;
    let expectedResult: IEcomStoreAddress | IEcomStoreAddress[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EcomStoreAddressService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EcomStoreAddress(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EcomStoreAddress', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomStoreAddress()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomStoreAddress', () => {
        const returnedFromService = Object.assign(
          {
            address1: 'BBBBBB',
            address2: 'BBBBBB',
            name: 'BBBBBB',
            phone: 'BBBBBB',
            emailAccId: 'BBBBBB',
            defaultAddress: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomStoreAddress', () => {
        const returnedFromService = Object.assign(
          {
            address1: 'BBBBBB',
            address2: 'BBBBBB',
            name: 'BBBBBB',
            phone: 'BBBBBB',
            emailAccId: 'BBBBBB',
            defaultAddress: true
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

      it('should delete a EcomStoreAddress', () => {
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
