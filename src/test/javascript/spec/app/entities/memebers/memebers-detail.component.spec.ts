import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AktywneGliwiceTestModule } from '../../../test.module';
import { MemebersDetailComponent } from 'app/entities/memebers/memebers-detail.component';
import { Memebers } from 'app/shared/model/memebers.model';

describe('Component Tests', () => {
  describe('Memebers Management Detail Component', () => {
    let comp: MemebersDetailComponent;
    let fixture: ComponentFixture<MemebersDetailComponent>;
    const route = ({ data: of({ memebers: new Memebers(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AktywneGliwiceTestModule],
        declarations: [MemebersDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MemebersDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MemebersDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load memebers on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.memebers).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
