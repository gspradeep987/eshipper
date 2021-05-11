import { ICountry } from 'app/entities/country/country.model';
import { IEcomStoreMarkup } from 'app/entities/ecom-store-markup/ecom-store-markup.model';

export interface IEcomMarkupQuaternary {
  id?: number;
  value?: number | null;
  country?: ICountry | null;
  ecomStoreMarkup?: IEcomStoreMarkup | null;
}

export class EcomMarkupQuaternary implements IEcomMarkupQuaternary {
  constructor(
    public id?: number,
    public value?: number | null,
    public country?: ICountry | null,
    public ecomStoreMarkup?: IEcomStoreMarkup | null
  ) {}
}

export function getEcomMarkupQuaternaryIdentifier(ecomMarkupQuaternary: IEcomMarkupQuaternary): number | undefined {
  return ecomMarkupQuaternary.id;
}
