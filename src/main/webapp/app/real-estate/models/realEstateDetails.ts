import { Location } from './location';
import { Reservation } from './reservation';
import { CategoryEnum } from 'app/real-estate/models/category';

export interface RealEstateDetails {
  name: string;
  description: string;
  location: Location;
  category: CategoryEnum;
  spectatorsCount: number;
  squareMeter: number;
  price: number;
  numberOfRooms: number;
  hasBalncony: boolean;
  hasAirCondition: boolean;
  ownerPhoneNumber: string;
  filePaths: Array<string>;
  reservations: Array<Reservation>;
  userId: number;
}
