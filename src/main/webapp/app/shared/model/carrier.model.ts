export interface ICarrier {
  id?: number;
}

export class Carrier implements ICarrier {
  constructor(public id?: number) {}
}
