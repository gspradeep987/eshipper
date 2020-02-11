export interface ICurrency {
  id?: number;
}

export class Currency implements ICurrency {
  constructor(public id?: number) {}
}
