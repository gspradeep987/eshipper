import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SisyphusJobTypeDetailComponent } from './sisyphus-job-type-detail.component';

describe('Component Tests', () => {
  describe('SisyphusJobType Management Detail Component', () => {
    let comp: SisyphusJobTypeDetailComponent;
    let fixture: ComponentFixture<SisyphusJobTypeDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [SisyphusJobTypeDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ sisyphusJobType: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(SisyphusJobTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SisyphusJobTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sisyphusJobType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sisyphusJobType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
