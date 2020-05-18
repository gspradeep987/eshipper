export interface IEcomStore {
  id?: number;
}

export class EcomStore implements IEcomStore {
  constructor(public id?: number) {}
}
