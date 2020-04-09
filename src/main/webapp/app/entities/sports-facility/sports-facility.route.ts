import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISportsFacility, SportsFacility } from 'app/shared/model/sports-facility.model';
import { SportsFacilityService } from './sports-facility.service';
import { SportsFacilityComponent } from './sports-facility.component';
import { SportsFacilityDetailComponent } from './sports-facility-detail.component';
import { SportsFacilityUpdateComponent } from './sports-facility-update.component';

@Injectable({ providedIn: 'root' })
export class SportsFacilityResolve implements Resolve<ISportsFacility> {
  constructor(private service: SportsFacilityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISportsFacility> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sportsFacility: HttpResponse<SportsFacility>) => {
          if (sportsFacility.body) {
            return of(sportsFacility.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SportsFacility());
  }
}

export const sportsFacilityRoute: Routes = [
  {
    path: '',
    component: SportsFacilityComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aktywneGliwiceApp.sportsFacility.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SportsFacilityDetailComponent,
    resolve: {
      sportsFacility: SportsFacilityResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aktywneGliwiceApp.sportsFacility.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SportsFacilityUpdateComponent,
    resolve: {
      sportsFacility: SportsFacilityResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aktywneGliwiceApp.sportsFacility.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SportsFacilityUpdateComponent,
    resolve: {
      sportsFacility: SportsFacilityResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aktywneGliwiceApp.sportsFacility.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
