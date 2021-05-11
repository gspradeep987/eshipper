export interface IShippingAddress {
  id?: number;
}

export class ShippingAddress implements IShippingAddress {
  constructor(public id?: number) {}
}

export function getShippingAddressIdentifier(shippingAddress: IShippingAddress): number | undefined {
  return shippingAddress.id;
}
