export interface IEcomStorePackageSettings {
  id?: number;
  packingInfo1?: boolean;
  packingInfo2?: boolean;
  packingInfo3?: boolean;
  packingInfo4?: boolean;
  ecomStoreId?: number;
}

export class EcomStorePackageSettings implements IEcomStorePackageSettings {
  constructor(
    public id?: number,
    public packingInfo1?: boolean,
    public packingInfo2?: boolean,
    public packingInfo3?: boolean,
    public packingInfo4?: boolean,
    public ecomStoreId?: number
  ) {
    this.packingInfo1 = this.packingInfo1 || false;
    this.packingInfo2 = this.packingInfo2 || false;
    this.packingInfo3 = this.packingInfo3 || false;
    this.packingInfo4 = this.packingInfo4 || false;
  }
}
