export interface ICarrierService {
  id?: number;
}

export class CarrierService implements ICarrierService {
  constructor(public id?: number) {}
}
