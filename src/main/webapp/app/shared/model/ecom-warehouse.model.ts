import { IEcomProduct } from 'app/shared/model/ecom-product.model';

export interface IEcomWarehouse {
  id?: number;
  name?: string;
  address?: string;
  ecomProducts?: IEcomProduct[];
}

export class EcomWarehouse implements IEcomWarehouse {
  constructor(public id?: number, public name?: string, public address?: string, public ecomProducts?: IEcomProduct[]) {}
}
