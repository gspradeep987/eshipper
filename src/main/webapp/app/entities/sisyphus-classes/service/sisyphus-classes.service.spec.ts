import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISisyphusClasses, SisyphusClasses } from '../sisyphus-classes.model';

import { SisyphusClassesService } from './sisyphus-classes.service';

describe('Service Tests', () => {
  describe('SisyphusClasses Service', () => {
    let service: SisyphusClassesService;
    let httpMock: HttpTestingController;
    let elemDefault: ISisyphusClasses;
    let expectedResult: ISisyphusClasses | ISisyphusClasses[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(SisyphusClassesService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        iD: 0,
        nAME: 'AAAAAAA',
        dESCRIPTION: 'AAAAAAA',
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

      it('should create a SisyphusClasses', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SisyphusClasses()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SisyphusClasses', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            iD: 1,
            nAME: 'BBBBBB',
            dESCRIPTION: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a SisyphusClasses', () => {
        const patchObject = Object.assign({}, new SisyphusClasses());

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SisyphusClasses', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            iD: 1,
            nAME: 'BBBBBB',
            dESCRIPTION: 'BBBBBB',
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

      it('should delete a SisyphusClasses', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addSisyphusClassesToCollectionIfMissing', () => {
        it('should add a SisyphusClasses to an empty array', () => {
          const sisyphusClasses: ISisyphusClasses = { id: 123 };
          expectedResult = service.addSisyphusClassesToCollectionIfMissing([], sisyphusClasses);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(sisyphusClasses);
        });

        it('should not add a SisyphusClasses to an array that contains it', () => {
          const sisyphusClasses: ISisyphusClasses = { id: 123 };
          const sisyphusClassesCollection: ISisyphusClasses[] = [
            {
              ...sisyphusClasses,
            },
            { id: 456 },
          ];
          expectedResult = service.addSisyphusClassesToCollectionIfMissing(sisyphusClassesCollection, sisyphusClasses);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a SisyphusClasses to an array that doesn't contain it", () => {
          const sisyphusClasses: ISisyphusClasses = { id: 123 };
          const sisyphusClassesCollection: ISisyphusClasses[] = [{ id: 456 }];
          expectedResult = service.addSisyphusClassesToCollectionIfMissing(sisyphusClassesCollection, sisyphusClasses);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(sisyphusClasses);
        });

        it('should add only unique SisyphusClasses to an array', () => {
          const sisyphusClassesArray: ISisyphusClasses[] = [{ id: 123 }, { id: 456 }, { id: 63303 }];
          const sisyphusClassesCollection: ISisyphusClasses[] = [{ id: 123 }];
          expectedResult = service.addSisyphusClassesToCollectionIfMissing(sisyphusClassesCollection, ...sisyphusClassesArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const sisyphusClasses: ISisyphusClasses = { id: 123 };
          const sisyphusClasses2: ISisyphusClasses = { id: 456 };
          expectedResult = service.addSisyphusClassesToCollectionIfMissing([], sisyphusClasses, sisyphusClasses2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(sisyphusClasses);
          expect(expectedResult).toContain(sisyphusClasses2);
        });

        it('should accept null and undefined values', () => {
          const sisyphusClasses: ISisyphusClasses = { id: 123 };
          expectedResult = service.addSisyphusClassesToCollectionIfMissing([], null, sisyphusClasses, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(sisyphusClasses);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
