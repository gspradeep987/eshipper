import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomMailTemplate, EcomMailTemplate } from '../ecom-mail-template.model';

import { EcomMailTemplateService } from './ecom-mail-template.service';

describe('Service Tests', () => {
  describe('EcomMailTemplate Service', () => {
    let service: EcomMailTemplateService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomMailTemplate;
    let expectedResult: IEcomMailTemplate | IEcomMailTemplate[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomMailTemplateService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        templateName: 'AAAAAAA',
        templateType: 'AAAAAAA',
        note: 'AAAAAAA',
        subject: 'AAAAAAA',
        content: 'AAAAAAA',
        isCustomTemplate: false,
        isOrder: false,
        isShipment: false,
        isProductPurchased: false,
        isAmountPaid: false,
        isStoreInfo: false,
        isBody1: false,
      };
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
            id: 0,
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
            id: 1,
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
            isBody1: true,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomMailTemplate', () => {
        const patchObject = Object.assign(
          {
            templateName: 'BBBBBB',
            templateType: 'BBBBBB',
            content: 'BBBBBB',
            isShipment: true,
            isStoreInfo: true,
          },
          new EcomMailTemplate()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomMailTemplate', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
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
            isBody1: true,
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

      describe('addEcomMailTemplateToCollectionIfMissing', () => {
        it('should add a EcomMailTemplate to an empty array', () => {
          const ecomMailTemplate: IEcomMailTemplate = { id: 123 };
          expectedResult = service.addEcomMailTemplateToCollectionIfMissing([], ecomMailTemplate);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomMailTemplate);
        });

        it('should not add a EcomMailTemplate to an array that contains it', () => {
          const ecomMailTemplate: IEcomMailTemplate = { id: 123 };
          const ecomMailTemplateCollection: IEcomMailTemplate[] = [
            {
              ...ecomMailTemplate,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomMailTemplateToCollectionIfMissing(ecomMailTemplateCollection, ecomMailTemplate);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomMailTemplate to an array that doesn't contain it", () => {
          const ecomMailTemplate: IEcomMailTemplate = { id: 123 };
          const ecomMailTemplateCollection: IEcomMailTemplate[] = [{ id: 456 }];
          expectedResult = service.addEcomMailTemplateToCollectionIfMissing(ecomMailTemplateCollection, ecomMailTemplate);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomMailTemplate);
        });

        it('should add only unique EcomMailTemplate to an array', () => {
          const ecomMailTemplateArray: IEcomMailTemplate[] = [{ id: 123 }, { id: 456 }, { id: 79004 }];
          const ecomMailTemplateCollection: IEcomMailTemplate[] = [{ id: 123 }];
          expectedResult = service.addEcomMailTemplateToCollectionIfMissing(ecomMailTemplateCollection, ...ecomMailTemplateArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomMailTemplate: IEcomMailTemplate = { id: 123 };
          const ecomMailTemplate2: IEcomMailTemplate = { id: 456 };
          expectedResult = service.addEcomMailTemplateToCollectionIfMissing([], ecomMailTemplate, ecomMailTemplate2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomMailTemplate);
          expect(expectedResult).toContain(ecomMailTemplate2);
        });

        it('should accept null and undefined values', () => {
          const ecomMailTemplate: IEcomMailTemplate = { id: 123 };
          expectedResult = service.addEcomMailTemplateToCollectionIfMissing([], null, ecomMailTemplate, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomMailTemplate);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
