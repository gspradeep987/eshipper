export interface IEcomorderAttachment {
  id?: number;
  name?: string;
  attachmentPath?: string;
  ecomOrderId?: number;
}

export class EcomorderAttachment implements IEcomorderAttachment {
  constructor(public id?: number, public name?: string, public attachmentPath?: string, public ecomOrderId?: number) {}
}
