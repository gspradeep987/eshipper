export interface IElasticStatus {
  id?: number;
}

export class ElasticStatus implements IElasticStatus {
  constructor(public id?: number) {}
}
