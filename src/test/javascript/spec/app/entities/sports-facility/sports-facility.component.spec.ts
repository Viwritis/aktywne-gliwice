import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AktywneGliwiceTestModule } from '../../../test.module';
import { SportsFacilityComponent } from 'app/entities/sports-facility/sports-facility.component';
import { SportsFacilityService } from 'app/entities/sports-facility/sports-facility.service';
import { SportsFacility } from 'app/shared/model/sports-facility.model';

describe('Component Tests', () => {
  describe('SportsFacility Management Component', () => {
    let comp: SportsFacilityComponent;
    let fixture: ComponentFixture<SportsFacilityComponent>;
    let service: SportsFacilityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AktywneGliwiceTestModule],
        declarations: [SportsFacilityComponent]
      })
        .overrideTemplate(SportsFacilityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SportsFacilityComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SportsFacilityService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SportsFacility(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sportsFacilities && comp.sportsFacilities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
