import { Category } from "./category";

export class Product {
  productId: bigint;
  name: string;
  productCode: string;
  productLink: string;
  description: string;
  price: number;
  quantity: bigint;
  active: boolean;
  category: Category;
}
