import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISisyphusClient, SisyphusClient } from '../sisyphus-client.model';

import { SisyphusClientService } from './sisyphus-client.service';

describe('Service Tests', () => {
  describe('SisyphusClient Service', () => {
    let service: SisyphusClientService;
    let httpMock: HttpTestingController;
    let elemDefault: ISisyphusClient;
    let expectedResult: ISisyphusClient | ISisyphusClient[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(SisyphusClientService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        name: 'AAAAAAA',
        contact: 'AAAAAAA',
        defaultFolder: 'AAAAAAA',
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

      it('should create a SisyphusClient', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SisyphusClient()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SisyphusClient', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            contact: 'BBBBBB',
            defaultFolder: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a SisyphusClient', () => {
        const patchObject = Object.assign({}, new SisyphusClient());

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SisyphusClient', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
            contact: 'BBBBBB',
            defaultFolder: 'BBBBBB',
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

      it('should delete a SisyphusClient', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addSisyphusClientToCollectionIfMissing', () => {
        it('should add a SisyphusClient to an empty array', () => {
          const sisyphusClient: ISisyphusClient = { id: 123 };
          expectedResult = service.addSisyphusClientToCollectionIfMissing([], sisyphusClient);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(sisyphusClient);
        });

        it('should not add a SisyphusClient to an array that contains it', () => {
          const sisyphusClient: ISisyphusClient = { id: 123 };
          const sisyphusClientCollection: ISisyphusClient[] = [
            {
              ...sisyphusClient,
            },
            { id: 456 },
          ];
          expectedResult = service.addSisyphusClientToCollectionIfMissing(sisyphusClientCollection, sisyphusClient);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a SisyphusClient to an array that doesn't contain it", () => {
          const sisyphusClient: ISisyphusClient = { id: 123 };
          const sisyphusClientCollection: ISisyphusClient[] = [{ id: 456 }];
          expectedResult = service.addSisyphusClientToCollectionIfMissing(sisyphusClientCollection, sisyphusClient);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(sisyphusClient);
        });

        it('should add only unique SisyphusClient to an array', () => {
          const sisyphusClientArray: ISisyphusClient[] = [{ id: 123 }, { id: 456 }, { id: 7615 }];
          const sisyphusClientCollection: ISisyphusClient[] = [{ id: 123 }];
          expectedResult = service.addSisyphusClientToCollectionIfMissing(sisyphusClientCollection, ...sisyphusClientArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const sisyphusClient: ISisyphusClient = { id: 123 };
          const sisyphusClient2: ISisyphusClient = { id: 456 };
          expectedResult = service.addSisyphusClientToCollectionIfMissing([], sisyphusClient, sisyphusClient2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(sisyphusClient);
          expect(expectedResult).toContain(sisyphusClient2);
        });

        it('should accept null and undefined values', () => {
          const sisyphusClient: ISisyphusClient = { id: 123 };
          expectedResult = service.addSisyphusClientToCollectionIfMissing([], null, sisyphusClient, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(sisyphusClient);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
