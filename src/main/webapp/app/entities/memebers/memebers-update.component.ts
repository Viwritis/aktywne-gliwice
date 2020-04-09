import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMemebers, Memebers } from 'app/shared/model/memebers.model';
import { MemebersService } from './memebers.service';
import { IExtandadUser } from 'app/shared/model/extandad-user.model';
import { ExtandadUserService } from 'app/entities/extandad-user/extandad-user.service';
import { ISportsFacility } from 'app/shared/model/sports-facility.model';
import { SportsFacilityService } from 'app/entities/sports-facility/sports-facility.service';

type SelectableEntity = IExtandadUser | ISportsFacility;

@Component({
  selector: 'jhi-memebers-update',
  templateUrl: './memebers-update.component.html'
})
export class MemebersUpdateComponent implements OnInit {
  isSaving = false;
  extandadusers: IExtandadUser[] = [];
  sportsfacilities: ISportsFacility[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    extandadUser: [],
    sportsFacility: []
  });

  constructor(
    protected memebersService: MemebersService,
    protected extandadUserService: ExtandadUserService,
    protected sportsFacilityService: SportsFacilityService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ memebers }) => {
      this.updateForm(memebers);

      this.extandadUserService.query().subscribe((res: HttpResponse<IExtandadUser[]>) => (this.extandadusers = res.body || []));

      this.sportsFacilityService.query().subscribe((res: HttpResponse<ISportsFacility[]>) => (this.sportsfacilities = res.body || []));
    });
  }

  updateForm(memebers: IMemebers): void {
    this.editForm.patchValue({
      id: memebers.id,
      name: memebers.name,
      extandadUser: memebers.extandadUser,
      sportsFacility: memebers.sportsFacility
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const memebers = this.createFromForm();
    if (memebers.id !== undefined) {
      this.subscribeToSaveResponse(this.memebersService.update(memebers));
    } else {
      this.subscribeToSaveResponse(this.memebersService.create(memebers));
    }
  }

  private createFromForm(): IMemebers {
    return {
      ...new Memebers(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      extandadUser: this.editForm.get(['extandadUser'])!.value,
      sportsFacility: this.editForm.get(['sportsFacility'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMemebers>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
