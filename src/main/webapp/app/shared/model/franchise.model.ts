export interface IFranchise {
  id?: number;
}

export class Franchise implements IFranchise {
  constructor(public id?: number) {}
}
