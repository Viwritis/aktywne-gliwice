import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AktywneGliwiceSharedModule } from 'app/shared/shared.module';
import { ExtandadUserComponent } from './extandad-user.component';
import { ExtandadUserDetailComponent } from './extandad-user-detail.component';
import { ExtandadUserUpdateComponent } from './extandad-user-update.component';
import { ExtandadUserDeleteDialogComponent } from './extandad-user-delete-dialog.component';
import { extandadUserRoute } from './extandad-user.route';

@NgModule({
  imports: [AktywneGliwiceSharedModule, RouterModule.forChild(extandadUserRoute)],
  declarations: [ExtandadUserComponent, ExtandadUserDetailComponent, ExtandadUserUpdateComponent, ExtandadUserDeleteDialogComponent],
  entryComponents: [ExtandadUserDeleteDialogComponent]
})
export class AktywneGliwiceExtandadUserModule {}
