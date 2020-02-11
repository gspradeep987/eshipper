import { IEcomProductImage } from 'app/shared/model/ecom-product-image.model';
import { IEcomWarehouse } from 'app/shared/model/ecom-warehouse.model';

export interface IEcomProduct {
  id?: number;
  title?: string;
  length?: number;
  width?: number;
  height?: number;
  weight?: number;
  dimUnit?: string;
  wghtUnit?: string;
  goodsValue?: number;
  productValue?: number;
  hsCodes?: string;
  sku?: string;
  policy?: string;
  insuranceAmt?: number;
  isInsured?: boolean;
  ecomProductImages?: IEcomProductImage[];
  countryId?: number;
  ecomWarehouses?: IEcomWarehouse[];
  ecomOrderId?: number;
}

export class EcomProduct implements IEcomProduct {
  constructor(
    public id?: number,
    public title?: string,
    public length?: number,
    public width?: number,
    public height?: number,
    public weight?: number,
    public dimUnit?: string,
    public wghtUnit?: string,
    public goodsValue?: number,
    public productValue?: number,
    public hsCodes?: string,
    public sku?: string,
    public policy?: string,
    public insuranceAmt?: number,
    public isInsured?: boolean,
    public ecomProductImages?: IEcomProductImage[],
    public countryId?: number,
    public ecomWarehouses?: IEcomWarehouse[],
    public ecomOrderId?: number
  ) {
    this.isInsured = this.isInsured || false;
  }
}
