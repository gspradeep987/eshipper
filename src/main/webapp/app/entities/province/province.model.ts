export interface IProvince {
  id?: number;
}

export class Province implements IProvince {
  constructor(public id?: number) {}
}

export function getProvinceIdentifier(province: IProvince): number | undefined {
  return province.id;
}
