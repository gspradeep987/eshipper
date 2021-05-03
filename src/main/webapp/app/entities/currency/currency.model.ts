export interface ICurrency {
  id?: number;
}

export class Currency implements ICurrency {
  constructor(public id?: number) {}
}

export function getCurrencyIdentifier(currency: ICurrency): number | undefined {
  return currency.id;
}
