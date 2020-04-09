import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMemebers } from 'app/shared/model/memebers.model';

@Component({
  selector: 'jhi-memebers-detail',
  templateUrl: './memebers-detail.component.html'
})
export class MemebersDetailComponent implements OnInit {
  memebers: IMemebers | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ memebers }) => (this.memebers = memebers));
  }

  previousState(): void {
    window.history.back();
  }
}
