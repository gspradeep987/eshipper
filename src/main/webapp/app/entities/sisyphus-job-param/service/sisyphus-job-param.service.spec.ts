import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISisyphusJobParam, SisyphusJobParam } from '../sisyphus-job-param.model';

import { SisyphusJobParamService } from './sisyphus-job-param.service';

describe('Service Tests', () => {
  describe('SisyphusJobParam Service', () => {
    let service: SisyphusJobParamService;
    let httpMock: HttpTestingController;
    let elemDefault: ISisyphusJobParam;
    let expectedResult: ISisyphusJobParam | ISisyphusJobParam[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(SisyphusJobParamService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        name: 'AAAAAAA',
        value: 'AAAAAAA',
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

      it('should create a SisyphusJobParam', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SisyphusJobParam()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SisyphusJobParam', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            value: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a SisyphusJobParam', () => {
        const patchObject = Object.assign({}, new SisyphusJobParam());

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SisyphusJobParam', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            value: 'BBBBBB',
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

      it('should delete a SisyphusJobParam', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addSisyphusJobParamToCollectionIfMissing', () => {
        it('should add a SisyphusJobParam to an empty array', () => {
          const sisyphusJobParam: ISisyphusJobParam = { id: 123 };
          expectedResult = service.addSisyphusJobParamToCollectionIfMissing([], sisyphusJobParam);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(sisyphusJobParam);
        });

        it('should not add a SisyphusJobParam to an array that contains it', () => {
          const sisyphusJobParam: ISisyphusJobParam = { id: 123 };
          const sisyphusJobParamCollection: ISisyphusJobParam[] = [
            {
              ...sisyphusJobParam,
            },
            { id: 456 },
          ];
          expectedResult = service.addSisyphusJobParamToCollectionIfMissing(sisyphusJobParamCollection, sisyphusJobParam);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a SisyphusJobParam to an array that doesn't contain it", () => {
          const sisyphusJobParam: ISisyphusJobParam = { id: 123 };
          const sisyphusJobParamCollection: ISisyphusJobParam[] = [{ id: 456 }];
          expectedResult = service.addSisyphusJobParamToCollectionIfMissing(sisyphusJobParamCollection, sisyphusJobParam);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(sisyphusJobParam);
        });

        it('should add only unique SisyphusJobParam to an array', () => {
          const sisyphusJobParamArray: ISisyphusJobParam[] = [{ id: 123 }, { id: 456 }, { id: 59904 }];
          const sisyphusJobParamCollection: ISisyphusJobParam[] = [{ id: 123 }];
          expectedResult = service.addSisyphusJobParamToCollectionIfMissing(sisyphusJobParamCollection, ...sisyphusJobParamArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const sisyphusJobParam: ISisyphusJobParam = { id: 123 };
          const sisyphusJobParam2: ISisyphusJobParam = { id: 456 };
          expectedResult = service.addSisyphusJobParamToCollectionIfMissing([], sisyphusJobParam, sisyphusJobParam2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(sisyphusJobParam);
          expect(expectedResult).toContain(sisyphusJobParam2);
        });

        it('should accept null and undefined values', () => {
          const sisyphusJobParam: ISisyphusJobParam = { id: 123 };
          expectedResult = service.addSisyphusJobParamToCollectionIfMissing([], null, sisyphusJobParam, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(sisyphusJobParam);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
