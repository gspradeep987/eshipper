export interface IEcomMailTemplate {
  id?: number;
  templateName?: string;
  templateType?: string;
  note?: string;
  subject?: string;
  content?: string;
  isCustomTemplate?: boolean;
  isOrder?: boolean;
  isShipment?: boolean;
  isProductPurchased?: boolean;
  isAmountPaid?: boolean;
  isStoreInfo?: boolean;
  isBody1?: boolean;
  ecomStoreId?: number;
}

export class EcomMailTemplate implements IEcomMailTemplate {
  constructor(
    public id?: number,
    public templateName?: string,
    public templateType?: string,
    public note?: string,
    public subject?: string,
    public content?: string,
    public isCustomTemplate?: boolean,
    public isOrder?: boolean,
    public isShipment?: boolean,
    public isProductPurchased?: boolean,
    public isAmountPaid?: boolean,
    public isStoreInfo?: boolean,
    public isBody1?: boolean,
    public ecomStoreId?: number
  ) {
    this.isCustomTemplate = this.isCustomTemplate || false;
    this.isOrder = this.isOrder || false;
    this.isShipment = this.isShipment || false;
    this.isProductPurchased = this.isProductPurchased || false;
    this.isAmountPaid = this.isAmountPaid || false;
    this.isStoreInfo = this.isStoreInfo || false;
    this.isBody1 = this.isBody1 || false;
  }
}
