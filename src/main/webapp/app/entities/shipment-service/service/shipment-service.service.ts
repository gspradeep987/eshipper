import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IShipmentService, getShipmentServiceIdentifier } from '../shipment-service.model';

export type EntityResponseType = HttpResponse<IShipmentService>;
export type EntityArrayResponseType = HttpResponse<IShipmentService[]>;

@Injectable({ providedIn: 'root' })
export class ShipmentServiceService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/shipment-services');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(shipmentService: IShipmentService): Observable<EntityResponseType> {
    return this.http.post<IShipmentService>(this.resourceUrl, shipmentService, { observe: 'response' });
  }

  update(shipmentService: IShipmentService): Observable<EntityResponseType> {
    return this.http.put<IShipmentService>(
      `${this.resourceUrl}/${getShipmentServiceIdentifier(shipmentService) as number}`,
      shipmentService,
      { observe: 'response' }
    );
  }

  partialUpdate(shipmentService: IShipmentService): Observable<EntityResponseType> {
    return this.http.patch<IShipmentService>(
      `${this.resourceUrl}/${getShipmentServiceIdentifier(shipmentService) as number}`,
      shipmentService,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IShipmentService>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IShipmentService[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addShipmentServiceToCollectionIfMissing(
    shipmentServiceCollection: IShipmentService[],
    ...shipmentServicesToCheck: (IShipmentService | null | undefined)[]
  ): IShipmentService[] {
    const shipmentServices: IShipmentService[] = shipmentServicesToCheck.filter(isPresent);
    if (shipmentServices.length > 0) {
      const shipmentServiceCollectionIdentifiers = shipmentServiceCollection.map(
        shipmentServiceItem => getShipmentServiceIdentifier(shipmentServiceItem)!
      );
      const shipmentServicesToAdd = shipmentServices.filter(shipmentServiceItem => {
        const shipmentServiceIdentifier = getShipmentServiceIdentifier(shipmentServiceItem);
        if (shipmentServiceIdentifier == null || shipmentServiceCollectionIdentifiers.includes(shipmentServiceIdentifier)) {
          return false;
        }
        shipmentServiceCollectionIdentifiers.push(shipmentServiceIdentifier);
        return true;
      });
      return [...shipmentServicesToAdd, ...shipmentServiceCollection];
    }
    return shipmentServiceCollection;
  }
}
