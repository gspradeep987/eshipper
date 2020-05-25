export interface IEcomOrderFullfillmentStatus {
  id?: number;
  name?: string;
  value?: string;
}

export class EcomOrderFullfillmentStatus implements IEcomOrderFullfillmentStatus {
  constructor(public id?: number, public name?: string, public value?: string) {}
}
