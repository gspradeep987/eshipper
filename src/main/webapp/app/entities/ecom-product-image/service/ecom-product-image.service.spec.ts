import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IEcomProductImage, EcomProductImage } from '../ecom-product-image.model';

import { EcomProductImageService } from './ecom-product-image.service';

describe('Service Tests', () => {
  describe('EcomProductImage Service', () => {
    let service: EcomProductImageService;
    let httpMock: HttpTestingController;
    let elemDefault: IEcomProductImage;
    let expectedResult: IEcomProductImage | IEcomProductImage[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(EcomProductImageService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        imageName: 'AAAAAAA',
        imagePath: 'AAAAAAA',
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

      it('should create a EcomProductImage', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new EcomProductImage()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a EcomProductImage', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            imageName: 'BBBBBB',
            imagePath: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a EcomProductImage', () => {
        const patchObject = Object.assign(
          {
            imagePath: 'BBBBBB',
          },
          new EcomProductImage()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of EcomProductImage', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            imageName: 'BBBBBB',
            imagePath: 'BBBBBB',
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

      it('should delete a EcomProductImage', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addEcomProductImageToCollectionIfMissing', () => {
        it('should add a EcomProductImage to an empty array', () => {
          const ecomProductImage: IEcomProductImage = { id: 123 };
          expectedResult = service.addEcomProductImageToCollectionIfMissing([], ecomProductImage);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomProductImage);
        });

        it('should not add a EcomProductImage to an array that contains it', () => {
          const ecomProductImage: IEcomProductImage = { id: 123 };
          const ecomProductImageCollection: IEcomProductImage[] = [
            {
              ...ecomProductImage,
            },
            { id: 456 },
          ];
          expectedResult = service.addEcomProductImageToCollectionIfMissing(ecomProductImageCollection, ecomProductImage);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a EcomProductImage to an array that doesn't contain it", () => {
          const ecomProductImage: IEcomProductImage = { id: 123 };
          const ecomProductImageCollection: IEcomProductImage[] = [{ id: 456 }];
          expectedResult = service.addEcomProductImageToCollectionIfMissing(ecomProductImageCollection, ecomProductImage);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomProductImage);
        });

        it('should add only unique EcomProductImage to an array', () => {
          const ecomProductImageArray: IEcomProductImage[] = [{ id: 123 }, { id: 456 }, { id: 41386 }];
          const ecomProductImageCollection: IEcomProductImage[] = [{ id: 123 }];
          expectedResult = service.addEcomProductImageToCollectionIfMissing(ecomProductImageCollection, ...ecomProductImageArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const ecomProductImage: IEcomProductImage = { id: 123 };
          const ecomProductImage2: IEcomProductImage = { id: 456 };
          expectedResult = service.addEcomProductImageToCollectionIfMissing([], ecomProductImage, ecomProductImage2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(ecomProductImage);
          expect(expectedResult).toContain(ecomProductImage2);
        });

        it('should accept null and undefined values', () => {
          const ecomProductImage: IEcomProductImage = { id: 123 };
          expectedResult = service.addEcomProductImageToCollectionIfMissing([], null, ecomProductImage, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(ecomProductImage);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
