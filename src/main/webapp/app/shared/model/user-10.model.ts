export interface IUser10 {
  id?: number;
  woSalesAgentId?: number;
}

export class User10 implements IUser10 {
  constructor(public id?: number, public woSalesAgentId?: number) {}
}
