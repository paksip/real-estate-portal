import { MapLocation } from './mapLocation';
import { CategoryEnum } from 'app/real-estate/models/category';

export interface NewRealEstate {
  name: string;
  category: CategoryEnum;
  location: MapLocation;
  description: string;
  squareMeter: number;
  price: number;
  numberOfRooms: number;
  hasBalcony: boolean;
  hasAirCondition: boolean;
  ownerPhoneNumber: string;
}
