import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExtandadUser } from 'app/shared/model/extandad-user.model';
import { ExtandadUserService } from './extandad-user.service';

@Component({
  templateUrl: './extandad-user-delete-dialog.component.html'
})
export class ExtandadUserDeleteDialogComponent {
  extandadUser?: IExtandadUser;

  constructor(
    protected extandadUserService: ExtandadUserService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.extandadUserService.delete(id).subscribe(() => {
      this.eventManager.broadcast('extandadUserListModification');
      this.activeModal.close();
    });
  }
}
