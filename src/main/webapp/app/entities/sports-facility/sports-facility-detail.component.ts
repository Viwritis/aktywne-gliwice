import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISportsFacility } from 'app/shared/model/sports-facility.model';

@Component({
  selector: 'jhi-sports-facility-detail',
  templateUrl: './sports-facility-detail.component.html'
})
export class SportsFacilityDetailComponent implements OnInit {
  sportsFacility: ISportsFacility | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sportsFacility }) => (this.sportsFacility = sportsFacility));
  }

  previousState(): void {
    window.history.back();
  }
}
