export interface IEcomStoreColorTheme {
  id?: number;
  primary?: string;
  secondary?: string;
  ecomStoreId?: number;
}

export class EcomStoreColorTheme implements IEcomStoreColorTheme {
  constructor(public id?: number, public primary?: string, public secondary?: string, public ecomStoreId?: number) {}
}
