export interface ICountry {
  id?: number;
}

export class Country implements ICountry {
  constructor(public id?: number) {}
}

export function getCountryIdentifier(country: ICountry): number | undefined {
  return country.id;
}
