import { IEcomStore } from 'app/entities/ecom-store/ecom-store.model';

export interface IEcomStoreColorTheme {
  id?: number;
  primary?: string | null;
  secondary?: string | null;
  ecomStore?: IEcomStore | null;
}

export class EcomStoreColorTheme implements IEcomStoreColorTheme {
  constructor(public id?: number, public primary?: string | null, public secondary?: string | null, public ecomStore?: IEcomStore | null) {}
}

export function getEcomStoreColorThemeIdentifier(ecomStoreColorTheme: IEcomStoreColorTheme): number | undefined {
  return ecomStoreColorTheme.id;
}
