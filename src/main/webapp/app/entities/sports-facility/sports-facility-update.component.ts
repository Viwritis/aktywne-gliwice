import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ISportsFacility, SportsFacility } from 'app/shared/model/sports-facility.model';
import { SportsFacilityService } from './sports-facility.service';
import { ILocation } from 'app/shared/model/location.model';
import { LocationService } from 'app/entities/location/location.service';

@Component({
  selector: 'jhi-sports-facility-update',
  templateUrl: './sports-facility-update.component.html'
})
export class SportsFacilityUpdateComponent implements OnInit {
  isSaving = false;
  locations: ILocation[] = [];

  editForm = this.fb.group({
    id: [],
    housingAssociationName: [null, [Validators.required]],
    type: [null, [Validators.required]],
    location: []
  });

  constructor(
    protected sportsFacilityService: SportsFacilityService,
    protected locationService: LocationService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sportsFacility }) => {
      this.updateForm(sportsFacility);

      this.locationService
        .query({ filter: 'sportsfacility-is-null' })
        .pipe(
          map((res: HttpResponse<ILocation[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ILocation[]) => {
          if (!sportsFacility.location || !sportsFacility.location.id) {
            this.locations = resBody;
          } else {
            this.locationService
              .find(sportsFacility.location.id)
              .pipe(
                map((subRes: HttpResponse<ILocation>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ILocation[]) => (this.locations = concatRes));
          }
        });
    });
  }

  updateForm(sportsFacility: ISportsFacility): void {
    this.editForm.patchValue({
      id: sportsFacility.id,
      housingAssociationName: sportsFacility.housingAssociationName,
      type: sportsFacility.type,
      location: sportsFacility.location
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sportsFacility = this.createFromForm();
    if (sportsFacility.id !== undefined) {
      this.subscribeToSaveResponse(this.sportsFacilityService.update(sportsFacility));
    } else {
      this.subscribeToSaveResponse(this.sportsFacilityService.create(sportsFacility));
    }
  }

  private createFromForm(): ISportsFacility {
    return {
      ...new SportsFacility(),
      id: this.editForm.get(['id'])!.value,
      housingAssociationName: this.editForm.get(['housingAssociationName'])!.value,
      type: this.editForm.get(['type'])!.value,
      location: this.editForm.get(['location'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISportsFacility>>): void {
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

  trackById(index: number, item: ILocation): any {
    return item.id;
  }
}
