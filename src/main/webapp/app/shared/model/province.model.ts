export interface IProvince {
  id?: number;
}

export class Province implements IProvince {
  constructor(public id?: number) {}
}
