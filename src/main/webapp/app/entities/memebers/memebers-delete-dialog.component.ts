import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMemebers } from 'app/shared/model/memebers.model';
import { MemebersService } from './memebers.service';

@Component({
  templateUrl: './memebers-delete-dialog.component.html'
})
export class MemebersDeleteDialogComponent {
  memebers?: IMemebers;

  constructor(protected memebersService: MemebersService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.memebersService.delete(id).subscribe(() => {
      this.eventManager.broadcast('memebersListModification');
      this.activeModal.close();
    });
  }
}
