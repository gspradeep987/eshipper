export interface IEcomStoreSync {
  id?: number;
  name?: string | null;
  syncFrequency?: number | null;
  syncInterval?: string | null;
}

export class EcomStoreSync implements IEcomStoreSync {
  constructor(public id?: number, public name?: string | null, public syncFrequency?: number | null, public syncInterval?: string | null) {}
}

export function getEcomStoreSyncIdentifier(ecomStoreSync: IEcomStoreSync): number | undefined {
  return ecomStoreSync.id;
}
