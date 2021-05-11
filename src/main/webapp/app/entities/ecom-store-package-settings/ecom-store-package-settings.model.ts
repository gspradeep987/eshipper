import { IEcomStore } from 'app/entities/ecom-store/ecom-store.model';

export interface IEcomStorePackageSettings {
  id?: number;
  packingInfo1?: boolean | null;
  packingInfo2?: boolean | null;
  packingInfo3?: boolean | null;
  packingInfo4?: boolean | null;
  ecomStore?: IEcomStore | null;
}

export class EcomStorePackageSettings implements IEcomStorePackageSettings {
  constructor(
    public id?: number,
    public packingInfo1?: boolean | null,
    public packingInfo2?: boolean | null,
    public packingInfo3?: boolean | null,
    public packingInfo4?: boolean | null,
    public ecomStore?: IEcomStore | null
  ) {
    this.packingInfo1 = this.packingInfo1 ?? false;
    this.packingInfo2 = this.packingInfo2 ?? false;
    this.packingInfo3 = this.packingInfo3 ?? false;
    this.packingInfo4 = this.packingInfo4 ?? false;
  }
}

export function getEcomStorePackageSettingsIdentifier(ecomStorePackageSettings: IEcomStorePackageSettings): number | undefined {
  return ecomStorePackageSettings.id;
}
