import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AktywneGliwiceSharedModule } from 'app/shared/shared.module';
import { SportsFacilityComponent } from './sports-facility.component';
import { SportsFacilityDetailComponent } from './sports-facility-detail.component';
import { SportsFacilityUpdateComponent } from './sports-facility-update.component';
import { SportsFacilityDeleteDialogComponent } from './sports-facility-delete-dialog.component';
import { sportsFacilityRoute } from './sports-facility.route';

@NgModule({
  imports: [AktywneGliwiceSharedModule, RouterModule.forChild(sportsFacilityRoute)],
  declarations: [
    SportsFacilityComponent,
    SportsFacilityDetailComponent,
    SportsFacilityUpdateComponent,
    SportsFacilityDeleteDialogComponent
  ],
  entryComponents: [SportsFacilityDeleteDialogComponent]
})
export class AktywneGliwiceSportsFacilityModule {}
