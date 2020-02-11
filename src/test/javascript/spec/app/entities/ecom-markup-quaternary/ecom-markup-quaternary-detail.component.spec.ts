import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomMarkupQuaternaryDetailComponent } from 'app/entities/ecom-markup-quaternary/ecom-markup-quaternary-detail.component';
import { EcomMarkupQuaternary } from 'app/shared/model/ecom-markup-quaternary.model';

describe('Component Tests', () => {
  describe('EcomMarkupQuaternary Management Detail Component', () => {
    let comp: EcomMarkupQuaternaryDetailComponent;
    let fixture: ComponentFixture<EcomMarkupQuaternaryDetailComponent>;
    const route = ({ data: of({ ecomMarkupQuaternary: new EcomMarkupQuaternary(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMarkupQuaternaryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomMarkupQuaternaryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomMarkupQuaternaryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomMarkupQuaternary on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomMarkupQuaternary).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
