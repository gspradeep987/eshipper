import { IEcomStoreMarkup } from 'app/entities/ecom-store-markup/ecom-store-markup.model';

export interface IEcomMarkupPrimary {
  id?: number;
  value?: number | null;
  fromLane?: string | null;
  toLane?: string | null;
  ecomStoreMarkup?: IEcomStoreMarkup | null;
}

export class EcomMarkupPrimary implements IEcomMarkupPrimary {
  constructor(
    public id?: number,
    public value?: number | null,
    public fromLane?: string | null,
    public toLane?: string | null,
    public ecomStoreMarkup?: IEcomStoreMarkup | null
  ) {}
}

export function getEcomMarkupPrimaryIdentifier(ecomMarkupPrimary: IEcomMarkupPrimary): number | undefined {
  return ecomMarkupPrimary.id;
}
