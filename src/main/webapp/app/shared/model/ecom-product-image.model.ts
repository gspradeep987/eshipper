export interface IEcomProductImage {
  id?: number;
  imageName?: string;
  imagePath?: string;
  ecomProductId?: number;
}

export class EcomProductImage implements IEcomProductImage {
  constructor(public id?: number, public imageName?: string, public imagePath?: string, public ecomProductId?: number) {}
}
