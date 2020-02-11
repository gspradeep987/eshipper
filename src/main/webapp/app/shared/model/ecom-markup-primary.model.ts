export interface IEcomMarkupPrimary {
  id?: number;
  value?: number;
  fromLane?: string;
  toLane?: string;
  ecomStoreMarkupId?: number;
}

export class EcomMarkupPrimary implements IEcomMarkupPrimary {
  constructor(
    public id?: number,
    public value?: number,
    public fromLane?: string,
    public toLane?: string,
    public ecomStoreMarkupId?: number
  ) {}
}
