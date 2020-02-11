export interface IEcomStoreAddress {
  id?: number;
  address1?: string;
  address2?: string;
  name?: string;
  phone?: string;
  emailAccId?: string;
  defaultAddress?: boolean;
  countryId?: number;
  provinceId?: number;
  cityId?: number;
  ecomStoreId?: number;
}

export class EcomStoreAddress implements IEcomStoreAddress {
  constructor(
    public id?: number,
    public address1?: string,
    public address2?: string,
    public name?: string,
    public phone?: string,
    public emailAccId?: string,
    public defaultAddress?: boolean,
    public countryId?: number,
    public provinceId?: number,
    public cityId?: number,
    public ecomStoreId?: number
  ) {
    this.defaultAddress = this.defaultAddress || false;
  }
}
