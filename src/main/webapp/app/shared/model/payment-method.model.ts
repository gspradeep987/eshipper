export interface IPaymentMethod {
  id?: number;
}

export class PaymentMethod implements IPaymentMethod {
  constructor(public id?: number) {}
}
