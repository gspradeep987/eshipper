import { IEcomMarkupPrimary } from 'app/entities/ecom-markup-primary/ecom-markup-primary.model';
import { IEcomMarkupSecondary } from 'app/entities/ecom-markup-secondary/ecom-markup-secondary.model';
import { IEcomMarkupTertiary } from 'app/entities/ecom-markup-tertiary/ecom-markup-tertiary.model';
import { IEcomMarkupQuaternary } from 'app/entities/ecom-markup-quaternary/ecom-markup-quaternary.model';
import { IEcomStore } from 'app/entities/ecom-store/ecom-store.model';

export interface IEcomStoreMarkup {
  id?: number;
  markupType?: string | null;
  minValue?: number | null;
  domesticValue?: number | null;
  intlValue?: number | null;
  flatRateValue?: number | null;
  opexValue?: number | null;
  ecomMarkupPrimary?: IEcomMarkupPrimary | null;
  ecomMarkupSecondary?: IEcomMarkupSecondary | null;
  ecomMarkupTertiary?: IEcomMarkupTertiary | null;
  ecomMarkupQuaternary?: IEcomMarkupQuaternary | null;
  ecomStore?: IEcomStore | null;
}

export class EcomStoreMarkup implements IEcomStoreMarkup {
  constructor(
    public id?: number,
    public markupType?: string | null,
    public minValue?: number | null,
    public domesticValue?: number | null,
    public intlValue?: number | null,
    public flatRateValue?: number | null,
    public opexValue?: number | null,
    public ecomMarkupPrimary?: IEcomMarkupPrimary | null,
    public ecomMarkupSecondary?: IEcomMarkupSecondary | null,
    public ecomMarkupTertiary?: IEcomMarkupTertiary | null,
    public ecomMarkupQuaternary?: IEcomMarkupQuaternary | null,
    public ecomStore?: IEcomStore | null
  ) {}
}

export function getEcomStoreMarkupIdentifier(ecomStoreMarkup: IEcomStoreMarkup): number | undefined {
  return ecomStoreMarkup.id;
}
