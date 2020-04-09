import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AktywneGliwiceTestModule } from '../../../test.module';
import { SportsFacilityUpdateComponent } from 'app/entities/sports-facility/sports-facility-update.component';
import { SportsFacilityService } from 'app/entities/sports-facility/sports-facility.service';
import { SportsFacility } from 'app/shared/model/sports-facility.model';

describe('Component Tests', () => {
  describe('SportsFacility Management Update Component', () => {
    let comp: SportsFacilityUpdateComponent;
    let fixture: ComponentFixture<SportsFacilityUpdateComponent>;
    let service: SportsFacilityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AktywneGliwiceTestModule],
        declarations: [SportsFacilityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SportsFacilityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SportsFacilityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SportsFacilityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SportsFacility(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new SportsFacility();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
