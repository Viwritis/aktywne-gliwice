import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMemebers } from 'app/shared/model/memebers.model';

type EntityResponseType = HttpResponse<IMemebers>;
type EntityArrayResponseType = HttpResponse<IMemebers[]>;

@Injectable({ providedIn: 'root' })
export class MemebersService {
  public resourceUrl = SERVER_API_URL + 'api/memebers';

  constructor(protected http: HttpClient) {}

  create(memebers: IMemebers): Observable<EntityResponseType> {
    return this.http.post<IMemebers>(this.resourceUrl, memebers, { observe: 'response' });
  }

  update(memebers: IMemebers): Observable<EntityResponseType> {
    return this.http.put<IMemebers>(this.resourceUrl, memebers, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMemebers>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMemebers[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
