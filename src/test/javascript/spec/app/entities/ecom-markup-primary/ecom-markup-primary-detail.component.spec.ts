import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomMarkupPrimaryDetailComponent } from 'app/entities/ecom-markup-primary/ecom-markup-primary-detail.component';
import { EcomMarkupPrimary } from 'app/shared/model/ecom-markup-primary.model';

describe('Component Tests', () => {
  describe('EcomMarkupPrimary Management Detail Component', () => {
    let comp: EcomMarkupPrimaryDetailComponent;
    let fixture: ComponentFixture<EcomMarkupPrimaryDetailComponent>;
    const route = ({ data: of({ ecomMarkupPrimary: new EcomMarkupPrimary(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMarkupPrimaryDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
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
