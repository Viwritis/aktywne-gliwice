import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AktywneGliwiceTestModule } from '../../../test.module';
import { ExtandadUserUpdateComponent } from 'app/entities/extandad-user/extandad-user-update.component';
import { ExtandadUserService } from 'app/entities/extandad-user/extandad-user.service';
import { ExtandadUser } from 'app/shared/model/extandad-user.model';

describe('Component Tests', () => {
  describe('ExtandadUser Management Update Component', () => {
    let comp: ExtandadUserUpdateComponent;
    let fixture: ComponentFixture<ExtandadUserUpdateComponent>;
    let service: ExtandadUserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AktywneGliwiceTestModule],
        declarations: [ExtandadUserUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ExtandadUserUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ExtandadUserUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ExtandadUserService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ExtandadUser(123);
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
        const entity = new ExtandadUser();
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
