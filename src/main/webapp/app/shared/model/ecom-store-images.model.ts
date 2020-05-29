export interface IEcomStoreImages {
  id?: number;
  name?: string;
  imagePath?: string;
}

export class EcomStoreImages implements IEcomStoreImages {
  constructor(public id?: number, public name?: string, public imagePath?: string) {}
}
