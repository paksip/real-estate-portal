export interface RealEstate {
  id: number;
  name: string;
  spectatorsCount: number;
  price: number;
  squareMeter: number;
  numberOfRooms: number;
  filePaths?: Array<string>;
}
