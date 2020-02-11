export interface IEcomStoreMarkup {
  id?: number;
  markupType?: string;
  minValue?: number;
  domesticValue?: number;
  intlValue?: number;
  flatRateValue?: number;
  opexValue?: number;
  ecomMarkupPrimaryId?: number;
  ecomMarkupSecondaryId?: number;
  ecomMarkupTertiaryId?: number;
  ecomMarkupQuaternaryId?: number;
  ecomStoreId?: number;
}

export class EcomStoreMarkup implements IEcomStoreMarkup {
  constructor(
    public id?: number,
    public markupType?: string,
    public minValue?: number,
    public domesticValue?: number,
    public intlValue?: number,
    public flatRateValue?: number,
    public opexValue?: number,
    public ecomMarkupPrimaryId?: number,
    public ecomMarkupSecondaryId?: number,
    public ecomMarkupTertiaryId?: number,
    public ecomMarkupQuaternaryId?: number,
    public ecomStoreId?: number
  ) {}
}
