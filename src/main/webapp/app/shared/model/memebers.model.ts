import { IExtandadUser } from 'app/shared/model/extandad-user.model';
import { ISportsFacility } from 'app/shared/model/sports-facility.model';

export interface IMemebers {
  id?: number;
  name?: string;
  extandadUser?: IExtandadUser;
  sportsFacility?: ISportsFacility;
}

export class Memebers implements IMemebers {
  constructor(public id?: number, public name?: string, public extandadUser?: IExtandadUser, public sportsFacility?: ISportsFacility) {}
}
