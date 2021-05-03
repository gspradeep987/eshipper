import { IEcomProductImage } from 'app/entities/ecom-product-image/ecom-product-image.model';
import { ICountry } from 'app/entities/country/country.model';
import { IEcomWarehouse } from 'app/entities/ecom-warehouse/ecom-warehouse.model';
import { IEcomOrder } from 'app/entities/ecom-order/ecom-order.model';

export interface IEcomProduct {
  id?: number;
  title?: string | null;
  length?: number | null;
  width?: number | null;
  height?: number | null;
  weight?: number | null;
  dimUnit?: string | null;
  wghtUnit?: string | null;
  goodsValue?: number | null;
  productValue?: number | null;
  hsCodes?: string | null;
  sku?: string | null;
  policy?: string | null;
  insuranceAmt?: number | null;
  isInsured?: boolean | null;
  ecomProductImages?: IEcomProductImage[] | null;
  country?: ICountry | null;
  ecomWarehouses?: IEcomWarehouse[] | null;
  ecomOrder?: IEcomOrder | null;
}

export class EcomProduct implements IEcomProduct {
  constructor(
    public id?: number,
    public title?: string | null,
    public length?: number | null,
    public width?: number | null,
    public height?: number | null,
    public weight?: number | null,
    public dimUnit?: string | null,
    public wghtUnit?: string | null,
    public goodsValue?: number | null,
    public productValue?: number | null,
    public hsCodes?: string | null,
    public sku?: string | null,
    public policy?: string | null,
    public insuranceAmt?: number | null,
    public isInsured?: boolean | null,
    public ecomProductImages?: IEcomProductImage[] | null,
    public country?: ICountry | null,
    public ecomWarehouses?: IEcomWarehouse[] | null,
    public ecomOrder?: IEcomOrder | null
  ) {
    this.isInsured = this.isInsured ?? false;
  }
}

export function getEcomProductIdentifier(ecomProduct: IEcomProduct): number | undefined {
  return ecomProduct.id;
}
