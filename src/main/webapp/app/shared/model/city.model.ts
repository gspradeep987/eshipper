export interface ICity {
  id?: number;
}

export class City implements ICity {
  constructor(public id?: number) {}
}
