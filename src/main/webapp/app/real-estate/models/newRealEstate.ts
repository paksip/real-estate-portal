import { Location } from './location';
import { CategoryEnum } from 'app/real-estate/models/category';

export interface NewRealEstate {
  name: string;
  category: CategoryEnum;
  location: Location;
  description: string;
  squareMeter: number;
  price: number;
  numberOfRooms: number;
  hasBalncony: boolean;
  hasAirCondition: boolean;
  ownerPhoneNumber: string;
}
