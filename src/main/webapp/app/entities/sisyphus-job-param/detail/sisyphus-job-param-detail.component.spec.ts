import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SisyphusJobParamDetailComponent } from './sisyphus-job-param-detail.component';

describe('Component Tests', () => {
  describe('SisyphusJobParam Management Detail Component', () => {
    let comp: SisyphusJobParamDetailComponent;
    let fixture: ComponentFixture<SisyphusJobParamDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [SisyphusJobParamDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ sisyphusJobParam: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(SisyphusJobParamDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SisyphusJobParamDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sisyphusJobParam on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sisyphusJobParam).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
