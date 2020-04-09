import { ILocation } from 'app/shared/model/location.model';
import { SportsFacilityType } from 'app/shared/model/enumerations/sports-facility-type.model';

export interface ISportsFacility {
  id?: number;
  housingAssociationName?: string;
  type?: SportsFacilityType;
  location?: ILocation;
}

export class SportsFacility implements ISportsFacility {
  constructor(public id?: number, public housingAssociationName?: string, public type?: SportsFacilityType, public location?: ILocation) {}
}
