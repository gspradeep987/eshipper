export interface IEcomMarkupSecondary {
  id?: number;
  value?: number;
  fromZip?: string;
  toZip?: string;
  ecomStoreMarkupId?: number;
}

export class EcomMarkupSecondary implements IEcomMarkupSecondary {
  constructor(
    public id?: number,
    public value?: number,
    public fromZip?: string,
    public toZip?: string,
    public ecomStoreMarkupId?: number
  ) {}
}
