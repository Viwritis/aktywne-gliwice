import { ICountry } from 'app/shared/model/country.model';

export interface ILocation {
  id?: number;
  streetAddress?: string;
  postalCode?: string;
  city?: string;
  stateProvince?: string;
  lat?: number;
  lng?: number;
  country?: ICountry;
}

export class Location implements ILocation {
  constructor(
    public id?: number,
    public streetAddress?: string,
    public postalCode?: string,
    public city?: string,
    public stateProvince?: string,
    public lat?: number,
    public lng?: number,
    public country?: ICountry
  ) {}
}
