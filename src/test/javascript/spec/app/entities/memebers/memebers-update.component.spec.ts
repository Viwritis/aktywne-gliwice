import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { AktywneGliwiceTestModule } from '../../../test.module';
import { MemebersUpdateComponent } from 'app/entities/memebers/memebers-update.component';
import { MemebersService } from 'app/entities/memebers/memebers.service';
import { Memebers } from 'app/shared/model/memebers.model';

describe('Component Tests', () => {
  describe('Memebers Management Update Component', () => {
    let comp: MemebersUpdateComponent;
    let fixture: ComponentFixture<MemebersUpdateComponent>;
    let service: MemebersService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AktywneGliwiceTestModule],
        declarations: [MemebersUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MemebersUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MemebersUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MemebersService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Memebers(123);
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
        const entity = new Memebers();
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
