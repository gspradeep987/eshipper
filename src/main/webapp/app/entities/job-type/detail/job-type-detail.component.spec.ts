import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JobTypeDetailComponent } from './job-type-detail.component';

describe('Component Tests', () => {
  describe('JobType Management Detail Component', () => {
    let comp: JobTypeDetailComponent;
    let fixture: ComponentFixture<JobTypeDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [JobTypeDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ jobType: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(JobTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(JobTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load jobType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.jobType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
