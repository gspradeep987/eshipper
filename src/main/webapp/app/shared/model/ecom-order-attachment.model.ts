export interface IEcomOrderAttachment {
  id?: number;
  name?: string;
  attachmentPath?: string;
  ecomOrderId?: number;
}

export class EcomOrderAttachment implements IEcomOrderAttachment {
  constructor(public id?: number, public name?: string, public attachmentPath?: string, public ecomOrderId?: number) {}
}
