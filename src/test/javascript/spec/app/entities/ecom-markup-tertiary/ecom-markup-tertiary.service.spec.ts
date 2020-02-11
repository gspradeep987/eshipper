import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EcomMarkupTertiaryService } from 'app/entities/ecom-markup-tertiary/ecom-markup-tertiary.service';
import { IEcomMarkupTertiary, EcomMarkupTertiary } from 'app/shared/model/ecom-markup-tertiary.model';

describe('Service Tests', () => {
  describe('EcomMarkupTertiary Service', () => {
    let injector: TestBed;
    let service: EcomMarkupTertiaryService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomMarkupTertiary;
    let expectedResult: IEcomMarkupTertiary | IEcomMarkupTertiary[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EcomMarkupTertiaryService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EcomMarkupTertiary(0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EcomMarkupTertiary', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomMarkupTertiary()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomMarkupTertiary', () => {
        const returnedFromService = Object.assign(
          {
            wgt1to5: 1,
            wgt6to10: 1,
            wgt11to15: 1,
            wgt16: 1
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomMarkupTertiary', () => {
        const returnedFromService = Object.assign(
          {
            wgt1to5: 1,
            wgt6to10: 1,
            wgt11to15: 1,
            wgt16: 1
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

      it('should delete a EcomMarkupTertiary', () => {
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
