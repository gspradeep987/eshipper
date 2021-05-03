export interface ICity {
  id?: number;
}

export class City implements ICity {
  constructor(public id?: number) {}
}

export function getCityIdentifier(city: ICity): number | undefined {
  return city.id;
}
