import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomMarkupPrimaryDetailComponent } from './ecom-markup-primary-detail.component';

describe('Component Tests', () => {
  describe('EcomMarkupPrimary Management Detail Component', () => {
    let comp: EcomMarkupPrimaryDetailComponent;
    let fixture: ComponentFixture<EcomMarkupPrimaryDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomMarkupPrimaryDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomMarkupPrimary: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(EcomMarkupPrimaryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomMarkupPrimaryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomMarkupPrimary on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomMarkupPrimary).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
