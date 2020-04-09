import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IExtandadUser, ExtandadUser } from 'app/shared/model/extandad-user.model';
import { ExtandadUserService } from './extandad-user.service';

@Component({
  selector: 'jhi-extandad-user-update',
  templateUrl: './extandad-user-update.component.html'
})
export class ExtandadUserUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    phoneNumber: []
  });

  constructor(protected extandadUserService: ExtandadUserService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ extandadUser }) => {
      this.updateForm(extandadUser);
    });
  }

  updateForm(extandadUser: IExtandadUser): void {
    this.editForm.patchValue({
      id: extandadUser.id,
      phoneNumber: extandadUser.phoneNumber
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const extandadUser = this.createFromForm();
    if (extandadUser.id !== undefined) {
      this.subscribeToSaveResponse(this.extandadUserService.update(extandadUser));
    } else {
      this.subscribeToSaveResponse(this.extandadUserService.create(extandadUser));
    }
  }

  private createFromForm(): IExtandadUser {
    return {
      ...new ExtandadUser(),
      id: this.editForm.get(['id'])!.value,
      phoneNumber: this.editForm.get(['phoneNumber'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExtandadUser>>): void {
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
}
