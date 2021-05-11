import { IEcomStore } from 'app/entities/ecom-store/ecom-store.model';

export interface IShipmentService {
  id?: number;
  name?: string | null;
  ecomStores?: IEcomStore[] | null;
}

export class ShipmentService implements IShipmentService {
  constructor(public id?: number, public name?: string | null, public ecomStores?: IEcomStore[] | null) {}
}

export function getShipmentServiceIdentifier(shipmentService: IShipmentService): number | undefined {
  return shipmentService.id;
}
