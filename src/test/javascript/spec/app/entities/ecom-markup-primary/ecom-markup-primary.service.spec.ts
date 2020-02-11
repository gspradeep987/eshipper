import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EcomMarkupPrimaryService } from 'app/entities/ecom-markup-primary/ecom-markup-primary.service';
import { IEcomMarkupPrimary, EcomMarkupPrimary } from 'app/shared/model/ecom-markup-primary.model';

describe('Service Tests', () => {
  describe('EcomMarkupPrimary Service', () => {
    let injector: TestBed;
    let service: EcomMarkupPrimaryService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomMarkupPrimary;
    let expectedResult: IEcomMarkupPrimary | IEcomMarkupPrimary[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EcomMarkupPrimaryService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EcomMarkupPrimary(0, 0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EcomMarkupPrimary', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomMarkupPrimary()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomMarkupPrimary', () => {
        const returnedFromService = Object.assign(
          {
            value: 1,
            fromLane: 'BBBBBB',
            toLane: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomMarkupPrimary', () => {
        const returnedFromService = Object.assign(
          {
            value: 1,
            fromLane: 'BBBBBB',
            toLane: 'BBBBBB'
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

      it('should delete a EcomMarkupPrimary', () => {
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
