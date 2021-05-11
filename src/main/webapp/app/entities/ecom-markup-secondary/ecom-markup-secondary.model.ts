import { IEcomStoreMarkup } from 'app/entities/ecom-store-markup/ecom-store-markup.model';

export interface IEcomMarkupSecondary {
  id?: number;
  value?: number | null;
  fromZip?: string | null;
  toZip?: string | null;
  ecomStoreMarkup?: IEcomStoreMarkup | null;
}

export class EcomMarkupSecondary implements IEcomMarkupSecondary {
  constructor(
    public id?: number,
    public value?: number | null,
    public fromZip?: string | null,
    public toZip?: string | null,
    public ecomStoreMarkup?: IEcomStoreMarkup | null
  ) {}
}

export function getEcomMarkupSecondaryIdentifier(ecomMarkupSecondary: IEcomMarkupSecondary): number | undefined {
  return ecomMarkupSecondary.id;
}
