import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IExtandadUser } from 'app/shared/model/extandad-user.model';

type EntityResponseType = HttpResponse<IExtandadUser>;
type EntityArrayResponseType = HttpResponse<IExtandadUser[]>;

@Injectable({ providedIn: 'root' })
export class ExtandadUserService {
  public resourceUrl = SERVER_API_URL + 'api/extandad-users';

  constructor(protected http: HttpClient) {}

  create(extandadUser: IExtandadUser): Observable<EntityResponseType> {
    return this.http.post<IExtandadUser>(this.resourceUrl, extandadUser, { observe: 'response' });
  }

  update(extandadUser: IExtandadUser): Observable<EntityResponseType> {
    return this.http.put<IExtandadUser>(this.resourceUrl, extandadUser, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IExtandadUser>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IExtandadUser[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
