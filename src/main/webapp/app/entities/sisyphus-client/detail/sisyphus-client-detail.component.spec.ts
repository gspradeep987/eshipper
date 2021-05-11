import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SisyphusClientDetailComponent } from './sisyphus-client-detail.component';

describe('Component Tests', () => {
  describe('SisyphusClient Management Detail Component', () => {
    let comp: SisyphusClientDetailComponent;
    let fixture: ComponentFixture<SisyphusClientDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [SisyphusClientDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ sisyphusClient: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(SisyphusClientDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SisyphusClientDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sisyphusClient on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sisyphusClient).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
