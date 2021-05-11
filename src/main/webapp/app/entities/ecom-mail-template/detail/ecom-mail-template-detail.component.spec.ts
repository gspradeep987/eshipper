import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomMailTemplateDetailComponent } from './ecom-mail-template-detail.component';

describe('Component Tests', () => {
  describe('EcomMailTemplate Management Detail Component', () => {
    let comp: EcomMailTemplateDetailComponent;
    let fixture: ComponentFixture<EcomMailTemplateDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomMailTemplateDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomMailTemplate: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(EcomMailTemplateDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomMailTemplateDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomMailTemplate on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomMailTemplate).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
