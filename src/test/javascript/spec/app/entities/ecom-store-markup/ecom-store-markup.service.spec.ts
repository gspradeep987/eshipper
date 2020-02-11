import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EcomStoreMarkupService } from 'app/entities/ecom-store-markup/ecom-store-markup.service';
import { IEcomStoreMarkup, EcomStoreMarkup } from 'app/shared/model/ecom-store-markup.model';

describe('Service Tests', () => {
  describe('EcomStoreMarkup Service', () => {
    let injector: TestBed;
    let service: EcomStoreMarkupService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomStoreMarkup;
    let expectedResult: IEcomStoreMarkup | IEcomStoreMarkup[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EcomStoreMarkupService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EcomStoreMarkup(0, 'AAAAAAA', 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EcomStoreMarkup', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomStoreMarkup()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomStoreMarkup', () => {
        const returnedFromService = Object.assign(
          {
            markupType: 'BBBBBB',
            minValue: 1,
            domesticValue: 1,
            intlValue: 1,
            flatRateValue: 1,
            opexValue: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomStoreMarkup', () => {
        const returnedFromService = Object.assign(
          {
            markupType: 'BBBBBB',
            minValue: 1,
            domesticValue: 1,
            intlValue: 1,
            flatRateValue: 1,
            opexValue: 1
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

      it('should delete a EcomStoreMarkup', () => {
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
