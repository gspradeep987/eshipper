import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomMarkupSecondaryDetailComponent } from 'app/entities/ecom-markup-secondary/ecom-markup-secondary-detail.component';
import { EcomMarkupSecondary } from 'app/shared/model/ecom-markup-secondary.model';

describe('Component Tests', () => {
  describe('EcomMarkupSecondary Management Detail Component', () => {
    let comp: EcomMarkupSecondaryDetailComponent;
    let fixture: ComponentFixture<EcomMarkupSecondaryDetailComponent>;
    const route = ({ data: of({ ecomMarkupSecondary: new EcomMarkupSecondary(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMarkupSecondaryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomMarkupSecondaryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomMarkupSecondaryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomMarkupSecondary on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomMarkupSecondary).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
