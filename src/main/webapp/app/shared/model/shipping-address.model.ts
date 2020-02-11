export interface IShippingAddress {
  id?: number;
}

export class ShippingAddress implements IShippingAddress {
  constructor(public id?: number) {}
}
