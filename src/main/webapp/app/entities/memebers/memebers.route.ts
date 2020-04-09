import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMemebers, Memebers } from 'app/shared/model/memebers.model';
import { MemebersService } from './memebers.service';
import { MemebersComponent } from './memebers.component';
import { MemebersDetailComponent } from './memebers-detail.component';
import { MemebersUpdateComponent } from './memebers-update.component';

@Injectable({ providedIn: 'root' })
export class MemebersResolve implements Resolve<IMemebers> {
  constructor(private service: MemebersService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMemebers> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((memebers: HttpResponse<Memebers>) => {
          if (memebers.body) {
            return of(memebers.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Memebers());
  }
}

export const memebersRoute: Routes = [
  {
    path: '',
    component: MemebersComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aktywneGliwiceApp.memebers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MemebersDetailComponent,
    resolve: {
      memebers: MemebersResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aktywneGliwiceApp.memebers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MemebersUpdateComponent,
    resolve: {
      memebers: MemebersResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aktywneGliwiceApp.memebers.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MemebersUpdateComponent,
    resolve: {
      memebers: MemebersResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aktywneGliwiceApp.memebers.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
