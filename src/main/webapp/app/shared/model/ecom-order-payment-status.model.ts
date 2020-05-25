export interface IEcomOrderPaymentStatus {
  id?: number;
  name?: string;
  value?: string;
}

export class EcomOrderPaymentStatus implements IEcomOrderPaymentStatus {
  constructor(public id?: number, public name?: string, public value?: string) {}
}
