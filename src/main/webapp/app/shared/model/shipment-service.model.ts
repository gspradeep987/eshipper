import { IEcomStore } from 'app/shared/model/ecom-store.model';

export interface IShipmentService {
  id?: number;
  name?: string;
  ecomStores?: IEcomStore[];
}

export class ShipmentService implements IShipmentService {
  constructor(public id?: number, public name?: string, public ecomStores?: IEcomStore[]) {}
}
