import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISportsFacility } from 'app/shared/model/sports-facility.model';
import { SportsFacilityService } from './sports-facility.service';
import { SportsFacilityDeleteDialogComponent } from './sports-facility-delete-dialog.component';

@Component({
  selector: 'jhi-sports-facility',
  templateUrl: './sports-facility.component.html'
})
export class SportsFacilityComponent implements OnInit, OnDestroy {
  sportsFacilities?: ISportsFacility[];
  eventSubscriber?: Subscription;

  constructor(
    protected sportsFacilityService: SportsFacilityService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.sportsFacilityService.query().subscribe((res: HttpResponse<ISportsFacility[]>) => (this.sportsFacilities = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSportsFacilities();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISportsFacility): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSportsFacilities(): void {
    this.eventSubscriber = this.eventManager.subscribe('sportsFacilityListModification', () => this.loadAll());
  }

  delete(sportsFacility: ISportsFacility): void {
    const modalRef = this.modalService.open(SportsFacilityDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sportsFacility = sportsFacility;
  }
}
