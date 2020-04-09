import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISportsFacility } from 'app/shared/model/sports-facility.model';
import { SportsFacilityService } from './sports-facility.service';

@Component({
  templateUrl: './sports-facility-delete-dialog.component.html'
})
export class SportsFacilityDeleteDialogComponent {
  sportsFacility?: ISportsFacility;

  constructor(
    protected sportsFacilityService: SportsFacilityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.sportsFacilityService.delete(id).subscribe(() => {
      this.eventManager.broadcast('sportsFacilityListModification');
      this.activeModal.close();
    });
  }
}
