import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SisyphusJobDetailComponent } from './sisyphus-job-detail.component';

describe('Component Tests', () => {
  describe('SisyphusJob Management Detail Component', () => {
    let comp: SisyphusJobDetailComponent;
    let fixture: ComponentFixture<SisyphusJobDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [SisyphusJobDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ sisyphusJob: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(SisyphusJobDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SisyphusJobDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sisyphusJob on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sisyphusJob).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
