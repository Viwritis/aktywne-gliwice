import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AktywneGliwiceTestModule } from '../../../test.module';
import { ExtandadUserDetailComponent } from 'app/entities/extandad-user/extandad-user-detail.component';
import { ExtandadUser } from 'app/shared/model/extandad-user.model';

describe('Component Tests', () => {
  describe('ExtandadUser Management Detail Component', () => {
    let comp: ExtandadUserDetailComponent;
    let fixture: ComponentFixture<ExtandadUserDetailComponent>;
    const route = ({ data: of({ extandadUser: new ExtandadUser(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AktywneGliwiceTestModule],
        declarations: [ExtandadUserDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ExtandadUserDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ExtandadUserDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load extandadUser on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.extandadUser).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
