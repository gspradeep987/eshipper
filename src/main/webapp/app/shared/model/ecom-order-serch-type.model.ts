export interface IEcomOrderSerchType {
  id?: number;
  name?: string;
  value?: string;
}

export class EcomOrderSerchType implements IEcomOrderSerchType {
  constructor(public id?: number, public name?: string, public value?: string) {}
}
