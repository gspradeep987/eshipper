import { ICountry } from 'app/entities/country/country.model';
import { IProvince } from 'app/entities/province/province.model';
import { ICity } from 'app/entities/city/city.model';
import { IEcomStore } from 'app/entities/ecom-store/ecom-store.model';

export interface IEcomStoreAddress {
  id?: number;
  address1?: string | null;
  address2?: string | null;
  name?: string | null;
  phone?: string | null;
  emailAccId?: string | null;
  defaultAddress?: boolean | null;
  country?: ICountry | null;
  province?: IProvince | null;
  city?: ICity | null;
  ecomStore?: IEcomStore | null;
}

export class EcomStoreAddress implements IEcomStoreAddress {
  constructor(
    public id?: number,
    public address1?: string | null,
    public address2?: string | null,
    public name?: string | null,
    public phone?: string | null,
    public emailAccId?: string | null,
    public defaultAddress?: boolean | null,
    public country?: ICountry | null,
    public province?: IProvince | null,
    public city?: ICity | null,
    public ecomStore?: IEcomStore | null
  ) {
    this.defaultAddress = this.defaultAddress ?? false;
  }
}

export function getEcomStoreAddressIdentifier(ecomStoreAddress: IEcomStoreAddress): number | undefined {
  return ecomStoreAddress.id;
}
