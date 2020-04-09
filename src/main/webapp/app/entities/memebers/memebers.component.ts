import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMemebers } from 'app/shared/model/memebers.model';
import { MemebersService } from './memebers.service';
import { MemebersDeleteDialogComponent } from './memebers-delete-dialog.component';

@Component({
  selector: 'jhi-memebers',
  templateUrl: './memebers.component.html'
})
export class MemebersComponent implements OnInit, OnDestroy {
  memebers?: IMemebers[];
  eventSubscriber?: Subscription;

  constructor(protected memebersService: MemebersService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.memebersService.query().subscribe((res: HttpResponse<IMemebers[]>) => (this.memebers = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMemebers();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMemebers): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMemebers(): void {
    this.eventSubscriber = this.eventManager.subscribe('memebersListModification', () => this.loadAll());
  }

  delete(memebers: IMemebers): void {
    const modalRef = this.modalService.open(MemebersDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.memebers = memebers;
  }
}
