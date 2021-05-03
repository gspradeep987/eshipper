import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IShipmentService, ShipmentService } from '../shipment-service.model';

import { ShipmentServiceService } from './shipment-service.service';

describe('Service Tests', () => {
  describe('ShipmentService Service', () => {
    let service: ShipmentServiceService;
    let httpMock: HttpTestingController;
    let elemDefault: IShipmentService;
    let expectedResult: IShipmentService | IShipmentService[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(ShipmentServiceService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        name: 'AAAAAAA',
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

      it('should create a ShipmentService', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ShipmentService()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ShipmentService', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a ShipmentService', () => {
        const patchObject = Object.assign(
          {
            name: 'BBBBBB',
          },
          new ShipmentService()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ShipmentService', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            name: 'BBBBBB',
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

      it('should delete a ShipmentService', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addShipmentServiceToCollectionIfMissing', () => {
        it('should add a ShipmentService to an empty array', () => {
          const shipmentService: IShipmentService = { id: 123 };
          expectedResult = service.addShipmentServiceToCollectionIfMissing([], shipmentService);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(shipmentService);
        });

        it('should not add a ShipmentService to an array that contains it', () => {
          const shipmentService: IShipmentService = { id: 123 };
          const shipmentServiceCollection: IShipmentService[] = [
            {
              ...shipmentService,
            },
            { id: 456 },
          ];
          expectedResult = service.addShipmentServiceToCollectionIfMissing(shipmentServiceCollection, shipmentService);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a ShipmentService to an array that doesn't contain it", () => {
          const shipmentService: IShipmentService = { id: 123 };
          const shipmentServiceCollection: IShipmentService[] = [{ id: 456 }];
          expectedResult = service.addShipmentServiceToCollectionIfMissing(shipmentServiceCollection, shipmentService);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(shipmentService);
        });

        it('should add only unique ShipmentService to an array', () => {
          const shipmentServiceArray: IShipmentService[] = [{ id: 123 }, { id: 456 }, { id: 22053 }];
          const shipmentServiceCollection: IShipmentService[] = [{ id: 123 }];
          expectedResult = service.addShipmentServiceToCollectionIfMissing(shipmentServiceCollection, ...shipmentServiceArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const shipmentService: IShipmentService = { id: 123 };
          const shipmentService2: IShipmentService = { id: 456 };
          expectedResult = service.addShipmentServiceToCollectionIfMissing([], shipmentService, shipmentService2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(shipmentService);
          expect(expectedResult).toContain(shipmentService2);
        });

        it('should accept null and undefined values', () => {
          const shipmentService: IShipmentService = { id: 123 };
          expectedResult = service.addShipmentServiceToCollectionIfMissing([], null, shipmentService, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(shipmentService);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
