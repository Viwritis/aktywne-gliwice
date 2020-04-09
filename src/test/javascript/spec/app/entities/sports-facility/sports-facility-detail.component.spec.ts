import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AktywneGliwiceTestModule } from '../../../test.module';
import { SportsFacilityDetailComponent } from 'app/entities/sports-facility/sports-facility-detail.component';
import { SportsFacility } from 'app/shared/model/sports-facility.model';

describe('Component Tests', () => {
  describe('SportsFacility Management Detail Component', () => {
    let comp: SportsFacilityDetailComponent;
    let fixture: ComponentFixture<SportsFacilityDetailComponent>;
    const route = ({ data: of({ sportsFacility: new SportsFacility(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AktywneGliwiceTestModule],
        declarations: [SportsFacilityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SportsFacilityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SportsFacilityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sportsFacility on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sportsFacility).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
