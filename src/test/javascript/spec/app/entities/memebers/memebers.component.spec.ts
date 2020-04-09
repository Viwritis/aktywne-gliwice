import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AktywneGliwiceTestModule } from '../../../test.module';
import { MemebersComponent } from 'app/entities/memebers/memebers.component';
import { MemebersService } from 'app/entities/memebers/memebers.service';
import { Memebers } from 'app/shared/model/memebers.model';

describe('Component Tests', () => {
  describe('Memebers Management Component', () => {
    let comp: MemebersComponent;
    let fixture: ComponentFixture<MemebersComponent>;
    let service: MemebersService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AktywneGliwiceTestModule],
        declarations: [MemebersComponent]
      })
        .overrideTemplate(MemebersComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MemebersComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MemebersService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Memebers(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.memebers && comp.memebers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
