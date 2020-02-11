export interface ICountry {
  id?: number;
}

export class Country implements ICountry {
  constructor(public id?: number) {}
}
