import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { AktywneGliwiceSharedModule } from 'app/shared/shared.module';
import { MemebersComponent } from './memebers.component';
import { MemebersDetailComponent } from './memebers-detail.component';
import { MemebersUpdateComponent } from './memebers-update.component';
import { MemebersDeleteDialogComponent } from './memebers-delete-dialog.component';
import { memebersRoute } from './memebers.route';

@NgModule({
  imports: [AktywneGliwiceSharedModule, RouterModule.forChild(memebersRoute)],
  declarations: [MemebersComponent, MemebersDetailComponent, MemebersUpdateComponent, MemebersDeleteDialogComponent],
  entryComponents: [MemebersDeleteDialogComponent]
})
export class AktywneGliwiceMemebersModule {}
