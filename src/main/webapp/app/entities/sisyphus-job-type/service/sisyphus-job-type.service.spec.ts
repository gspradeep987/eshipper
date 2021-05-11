import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISisyphusJobType, SisyphusJobType } from '../sisyphus-job-type.model';

import { SisyphusJobTypeService } from './sisyphus-job-type.service';

describe('Service Tests', () => {
  describe('SisyphusJobType Service', () => {
    let service: SisyphusJobTypeService;
    let httpMock: HttpTestingController;
    let elemDefault: ISisyphusJobType;
    let expectedResult: ISisyphusJobType | ISisyphusJobType[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(SisyphusJobTypeService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        name: 'AAAAAAA',
        description: 'AAAAAAA',
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

      it('should create a SisyphusJobType', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SisyphusJobType()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SisyphusJobType', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            description: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a SisyphusJobType', () => {
        const patchObject = Object.assign(
          {
            description: 'BBBBBB',
          },
          new SisyphusJobType()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SisyphusJobType', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            description: 'BBBBBB',
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

      it('should delete a SisyphusJobType', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addSisyphusJobTypeToCollectionIfMissing', () => {
        it('should add a SisyphusJobType to an empty array', () => {
          const sisyphusJobType: ISisyphusJobType = { id: 123 };
          expectedResult = service.addSisyphusJobTypeToCollectionIfMissing([], sisyphusJobType);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(sisyphusJobType);
        });

        it('should not add a SisyphusJobType to an array that contains it', () => {
          const sisyphusJobType: ISisyphusJobType = { id: 123 };
          const sisyphusJobTypeCollection: ISisyphusJobType[] = [
            {
              ...sisyphusJobType,
            },
            { id: 456 },
          ];
          expectedResult = service.addSisyphusJobTypeToCollectionIfMissing(sisyphusJobTypeCollection, sisyphusJobType);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a SisyphusJobType to an array that doesn't contain it", () => {
          const sisyphusJobType: ISisyphusJobType = { id: 123 };
          const sisyphusJobTypeCollection: ISisyphusJobType[] = [{ id: 456 }];
          expectedResult = service.addSisyphusJobTypeToCollectionIfMissing(sisyphusJobTypeCollection, sisyphusJobType);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(sisyphusJobType);
        });

        it('should add only unique SisyphusJobType to an array', () => {
          const sisyphusJobTypeArray: ISisyphusJobType[] = [{ id: 123 }, { id: 456 }, { id: 67240 }];
          const sisyphusJobTypeCollection: ISisyphusJobType[] = [{ id: 123 }];
          expectedResult = service.addSisyphusJobTypeToCollectionIfMissing(sisyphusJobTypeCollection, ...sisyphusJobTypeArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const sisyphusJobType: ISisyphusJobType = { id: 123 };
          const sisyphusJobType2: ISisyphusJobType = { id: 456 };
          expectedResult = service.addSisyphusJobTypeToCollectionIfMissing([], sisyphusJobType, sisyphusJobType2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(sisyphusJobType);
          expect(expectedResult).toContain(sisyphusJobType2);
        });

        it('should accept null and undefined values', () => {
          const sisyphusJobType: ISisyphusJobType = { id: 123 };
          expectedResult = service.addSisyphusJobTypeToCollectionIfMissing([], null, sisyphusJobType, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(sisyphusJobType);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
