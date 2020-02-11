import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EcomProductService } from 'app/entities/ecom-product/ecom-product.service';
import { IEcomProduct, EcomProduct } from 'app/shared/model/ecom-product.model';

describe('Service Tests', () => {
  describe('EcomProduct Service', () => {
    let injector: TestBed;
    let service: EcomProductService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomProduct;
    let expectedResult: IEcomProduct | IEcomProduct[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EcomProductService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EcomProduct(0, 'AAAAAAA', 0, 0, 0, 0, 'AAAAAAA', 'AAAAAAA', 0, 0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, false);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EcomProduct', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomProduct()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomProduct', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            length: 1,
            width: 1,
            height: 1,
            weight: 1,
            dimUnit: 'BBBBBB',
            wghtUnit: 'BBBBBB',
            goodsValue: 1,
            productValue: 1,
            hsCodes: 'BBBBBB',
            sku: 'BBBBBB',
            policy: 'BBBBBB',
            insuranceAmt: 1,
            isInsured: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomProduct', () => {
        const returnedFromService = Object.assign(
          {
            title: 'BBBBBB',
            length: 1,
            width: 1,
            height: 1,
            weight: 1,
            dimUnit: 'BBBBBB',
            wghtUnit: 'BBBBBB',
            goodsValue: 1,
            productValue: 1,
            hsCodes: 'BBBBBB',
            sku: 'BBBBBB',
            policy: 'BBBBBB',
            insuranceAmt: 1,
            isInsured: true
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

      it('should delete a EcomProduct', () => {
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
