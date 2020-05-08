export interface IEcomOrder {
  id?: number;
}

export class EcomOrder implements IEcomOrder {
  constructor(public id?: number) {}
}
