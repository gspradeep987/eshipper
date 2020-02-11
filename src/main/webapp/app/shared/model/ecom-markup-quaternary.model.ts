export interface IEcomMarkupQuaternary {
  id?: number;
  value?: number;
  countryId?: number;
  ecomStoreMarkupId?: number;
}

export class EcomMarkupQuaternary implements IEcomMarkupQuaternary {
  constructor(public id?: number, public value?: number, public countryId?: number, public ecomStoreMarkupId?: number) {}
}
