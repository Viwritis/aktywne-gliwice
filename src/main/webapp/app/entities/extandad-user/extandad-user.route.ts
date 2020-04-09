import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IExtandadUser, ExtandadUser } from 'app/shared/model/extandad-user.model';
import { ExtandadUserService } from './extandad-user.service';
import { ExtandadUserComponent } from './extandad-user.component';
import { ExtandadUserDetailComponent } from './extandad-user-detail.component';
import { ExtandadUserUpdateComponent } from './extandad-user-update.component';

@Injectable({ providedIn: 'root' })
export class ExtandadUserResolve implements Resolve<IExtandadUser> {
  constructor(private service: ExtandadUserService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IExtandadUser> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((extandadUser: HttpResponse<ExtandadUser>) => {
          if (extandadUser.body) {
            return of(extandadUser.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ExtandadUser());
  }
}

export const extandadUserRoute: Routes = [
  {
    path: '',
    component: ExtandadUserComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aktywneGliwiceApp.extandadUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ExtandadUserDetailComponent,
    resolve: {
      extandadUser: ExtandadUserResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aktywneGliwiceApp.extandadUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ExtandadUserUpdateComponent,
    resolve: {
      extandadUser: ExtandadUserResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aktywneGliwiceApp.extandadUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ExtandadUserUpdateComponent,
    resolve: {
      extandadUser: ExtandadUserResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'aktywneGliwiceApp.extandadUser.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
