import { IEcomProduct } from 'app/entities/ecom-product/ecom-product.model';

export interface IEcomWarehouse {
  id?: number;
  name?: string | null;
  address?: string | null;
  ecomProducts?: IEcomProduct[] | null;
}

export class EcomWarehouse implements IEcomWarehouse {
  constructor(
    public id?: number,
    public name?: string | null,
    public address?: string | null,
    public ecomProducts?: IEcomProduct[] | null
  ) {}
}

export function getEcomWarehouseIdentifier(ecomWarehouse: IEcomWarehouse): number | undefined {
  return ecomWarehouse.id;
}
