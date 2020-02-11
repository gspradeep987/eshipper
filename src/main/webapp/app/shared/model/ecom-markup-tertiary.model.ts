export interface IEcomMarkupTertiary {
  id?: number;
  wgt1to5?: number;
  wgt6to10?: number;
  wgt11to15?: number;
  wgt16?: number;
  ecomStoreMarkupId?: number;
}

export class EcomMarkupTertiary implements IEcomMarkupTertiary {
  constructor(
    public id?: number,
    public wgt1to5?: number,
    public wgt6to10?: number,
    public wgt11to15?: number,
    public wgt16?: number,
    public ecomStoreMarkupId?: number
  ) {}
}
