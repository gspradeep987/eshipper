import { IEcomProduct } from 'app/entities/ecom-product/ecom-product.model';

export interface IEcomProductImage {
  id?: number;
  imageName?: string | null;
  imagePath?: string | null;
  ecomProduct?: IEcomProduct | null;
}

export class EcomProductImage implements IEcomProductImage {
  constructor(
    public id?: number,
    public imageName?: string | null,
    public imagePath?: string | null,
    public ecomProduct?: IEcomProduct | null
  ) {}
}

export function getEcomProductImageIdentifier(ecomProductImage: IEcomProductImage): number | undefined {
  return ecomProductImage.id;
}
