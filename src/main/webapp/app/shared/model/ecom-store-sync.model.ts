export interface IEcomStoreSync {
  id?: number;
  name?: string;
  syncFrequency?: number;
  syncInterval?: string;
}

export class EcomStoreSync implements IEcomStoreSync {
  constructor(public id?: number, public name?: string, public syncFrequency?: number, public syncInterval?: string) {}
}
