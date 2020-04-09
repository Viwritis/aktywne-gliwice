import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExtandadUser } from 'app/shared/model/extandad-user.model';

@Component({
  selector: 'jhi-extandad-user-detail',
  templateUrl: './extandad-user-detail.component.html'
})
export class ExtandadUserDetailComponent implements OnInit {
  extandadUser: IExtandadUser | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ extandadUser }) => (this.extandadUser = extandadUser));
  }

  previousState(): void {
    window.history.back();
  }
}
