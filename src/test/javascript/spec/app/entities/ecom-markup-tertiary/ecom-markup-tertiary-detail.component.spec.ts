import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomMarkupTertiaryDetailComponent } from 'app/entities/ecom-markup-tertiary/ecom-markup-tertiary-detail.component';
import { EcomMarkupTertiary } from 'app/shared/model/ecom-markup-tertiary.model';

describe('Component Tests', () => {
  describe('EcomMarkupTertiary Management Detail Component', () => {
    let comp: EcomMarkupTertiaryDetailComponent;
    let fixture: ComponentFixture<EcomMarkupTertiaryDetailComponent>;
    const route = ({ data: of({ ecomMarkupTertiary: new EcomMarkupTertiary(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMarkupTertiaryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomMarkupTertiaryDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomMarkupTertiaryDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomMarkupTertiary on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomMarkupTertiary).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
