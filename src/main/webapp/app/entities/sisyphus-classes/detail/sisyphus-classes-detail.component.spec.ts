import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SisyphusClassesDetailComponent } from './sisyphus-classes-detail.component';

describe('Component Tests', () => {
  describe('SisyphusClasses Management Detail Component', () => {
    let comp: SisyphusClassesDetailComponent;
    let fixture: ComponentFixture<SisyphusClassesDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [SisyphusClassesDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ sisyphusClasses: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(SisyphusClassesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SisyphusClassesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sisyphusClasses on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sisyphusClasses).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
