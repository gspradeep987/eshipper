import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SisyphusSubJobDetailComponent } from './sisyphus-sub-job-detail.component';

describe('Component Tests', () => {
  describe('SisyphusSubJob Management Detail Component', () => {
    let comp: SisyphusSubJobDetailComponent;
    let fixture: ComponentFixture<SisyphusSubJobDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [SisyphusSubJobDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ sisyphusSubJob: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(SisyphusSubJobDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SisyphusSubJobDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sisyphusSubJob on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sisyphusSubJob).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
