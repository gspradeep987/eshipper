import { IEcomStoreMarkup } from 'app/entities/ecom-store-markup/ecom-store-markup.model';

export interface IEcomMarkupTertiary {
  id?: number;
  wgt1to5?: number | null;
  wgt6to10?: number | null;
  wgt11to15?: number | null;
  wgt16?: number | null;
  ecomStoreMarkup?: IEcomStoreMarkup | null;
}

export class EcomMarkupTertiary implements IEcomMarkupTertiary {
  constructor(
    public id?: number,
    public wgt1to5?: number | null,
    public wgt6to10?: number | null,
    public wgt11to15?: number | null,
    public wgt16?: number | null,
    public ecomStoreMarkup?: IEcomStoreMarkup | null
  ) {}
}

export function getEcomMarkupTertiaryIdentifier(ecomMarkupTertiary: IEcomMarkupTertiary): number | undefined {
  return ecomMarkupTertiary.id;
}
