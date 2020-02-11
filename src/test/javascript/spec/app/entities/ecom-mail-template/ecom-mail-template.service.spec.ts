import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { EcomMailTemplateService } from 'app/entities/ecom-mail-template/ecom-mail-template.service';
import { IEcomMailTemplate, EcomMailTemplate } from 'app/shared/model/ecom-mail-template.model';

describe('Service Tests', () => {
  describe('EcomMailTemplate Service', () => {
    let injector: TestBed;
    let service: EcomMailTemplateService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomMailTemplate;
    let expectedResult: IEcomMailTemplate | IEcomMailTemplate[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(EcomMailTemplateService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new EcomMailTemplate(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        false,
        false,
        false,
        false,
        false,
        false,
        false
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a EcomMailTemplate', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomMailTemplate()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomMailTemplate', () => {
        const returnedFromService = Object.assign(
          {
            templateName: 'BBBBBB',
            templateType: 'BBBBBB',
            note: 'BBBBBB',
            subject: 'BBBBBB',
            content: 'BBBBBB',
            isCustomTemplate: true,
            isOrder: true,
            isShipment: true,
            isProductPurchased: true,
            isAmountPaid: true,
            isStoreInfo: true,
            isBody1: true
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomMailTemplate', () => {
        const returnedFromService = Object.assign(
          {
            templateName: 'BBBBBB',
            templateType: 'BBBBBB',
            note: 'BBBBBB',
            subject: 'BBBBBB',
            content: 'BBBBBB',
            isCustomTemplate: true,
            isOrder: true,
            isShipment: true,
            isProductPurchased: true,
            isAmountPaid: true,
            isStoreInfo: true,
            isBody1: true
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

      it('should delete a EcomMailTemplate', () => {
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
