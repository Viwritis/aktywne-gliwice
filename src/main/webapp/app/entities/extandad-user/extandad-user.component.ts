import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IExtandadUser } from 'app/shared/model/extandad-user.model';
import { ExtandadUserService } from './extandad-user.service';
import { ExtandadUserDeleteDialogComponent } from './extandad-user-delete-dialog.component';

@Component({
  selector: 'jhi-extandad-user',
  templateUrl: './extandad-user.component.html'
})
export class ExtandadUserComponent implements OnInit, OnDestroy {
  extandadUsers?: IExtandadUser[];
  eventSubscriber?: Subscription;

  constructor(
    protected extandadUserService: ExtandadUserService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.extandadUserService.query().subscribe((res: HttpResponse<IExtandadUser[]>) => (this.extandadUsers = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInExtandadUsers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IExtandadUser): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInExtandadUsers(): void {
    this.eventSubscriber = this.eventManager.subscribe('extandadUserListModification', () => this.loadAll());
  }

  delete(extandadUser: IExtandadUser): void {
    const modalRef = this.modalService.open(ExtandadUserDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.extandadUser = extandadUser;
  }
}
