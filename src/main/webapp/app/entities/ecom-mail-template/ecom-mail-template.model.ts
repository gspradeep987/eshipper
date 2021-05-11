import { IEcomStore } from 'app/entities/ecom-store/ecom-store.model';

export interface IEcomMailTemplate {
  id?: number;
  templateName?: string | null;
  templateType?: string | null;
  note?: string | null;
  subject?: string | null;
  content?: string | null;
  isCustomTemplate?: boolean | null;
  isOrder?: boolean | null;
  isShipment?: boolean | null;
  isProductPurchased?: boolean | null;
  isAmountPaid?: boolean | null;
  isStoreInfo?: boolean | null;
  isBody1?: boolean | null;
  ecomStore?: IEcomStore | null;
}

export class EcomMailTemplate implements IEcomMailTemplate {
  constructor(
    public id?: number,
    public templateName?: string | null,
    public templateType?: string | null,
    public note?: string | null,
    public subject?: string | null,
    public content?: string | null,
    public isCustomTemplate?: boolean | null,
    public isOrder?: boolean | null,
    public isShipment?: boolean | null,
    public isProductPurchased?: boolean | null,
    public isAmountPaid?: boolean | null,
    public isStoreInfo?: boolean | null,
    public isBody1?: boolean | null,
    public ecomStore?: IEcomStore | null
  ) {
    this.isCustomTemplate = this.isCustomTemplate ?? false;
    this.isOrder = this.isOrder ?? false;
    this.isShipment = this.isShipment ?? false;
    this.isProductPurchased = this.isProductPurchased ?? false;
    this.isAmountPaid = this.isAmountPaid ?? false;
    this.isStoreInfo = this.isStoreInfo ?? false;
    this.isBody1 = this.isBody1 ?? false;
  }
}

export function getEcomMailTemplateIdentifier(ecomMailTemplate: IEcomMailTemplate): number | undefined {
  return ecomMailTemplate.id;
}
