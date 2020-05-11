export interface IPaymentMethod {
  id?: number;
  woSalesAgentDetailsId?: number;
}

export class PaymentMethod implements IPaymentMethod {
  constructor(public id?: number, public woSalesAgentDetailsId?: number) {}
}
