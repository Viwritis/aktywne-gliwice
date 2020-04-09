import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISportsFacility } from 'app/shared/model/sports-facility.model';

type EntityResponseType = HttpResponse<ISportsFacility>;
type EntityArrayResponseType = HttpResponse<ISportsFacility[]>;

@Injectable({ providedIn: 'root' })
export class SportsFacilityService {
  public resourceUrl = SERVER_API_URL + 'api/sports-facilities';

  constructor(protected http: HttpClient) {}

  create(sportsFacility: ISportsFacility): Observable<EntityResponseType> {
    return this.http.post<ISportsFacility>(this.resourceUrl, sportsFacility, { observe: 'response' });
  }

  update(sportsFacility: ISportsFacility): Observable<EntityResponseType> {
    return this.http.put<ISportsFacility>(this.resourceUrl, sportsFacility, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISportsFacility>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISportsFacility[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
