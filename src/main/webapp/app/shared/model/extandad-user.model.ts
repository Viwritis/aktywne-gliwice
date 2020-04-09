export interface IExtandadUser {
  id?: number;
  phoneNumber?: string;
}

export class ExtandadUser implements IExtandadUser {
  constructor(public id?: number, public phoneNumber?: string) {}
}
