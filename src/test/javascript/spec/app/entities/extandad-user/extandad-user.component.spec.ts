import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AktywneGliwiceTestModule } from '../../../test.module';
import { ExtandadUserComponent } from 'app/entities/extandad-user/extandad-user.component';
import { ExtandadUserService } from 'app/entities/extandad-user/extandad-user.service';
import { ExtandadUser } from 'app/shared/model/extandad-user.model';

describe('Component Tests', () => {
  describe('ExtandadUser Management Component', () => {
    let comp: ExtandadUserComponent;
    let fixture: ComponentFixture<ExtandadUserComponent>;
    let service: ExtandadUserService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AktywneGliwiceTestModule],
        declarations: [ExtandadUserComponent]
      })
        .overrideTemplate(ExtandadUserComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ExtandadUserComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ExtandadUserService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ExtandadUser(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.extandadUsers && comp.extandadUsers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
