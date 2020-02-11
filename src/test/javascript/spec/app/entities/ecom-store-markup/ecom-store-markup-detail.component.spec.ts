import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreMarkupDetailComponent } from 'app/entities/ecom-store-markup/ecom-store-markup-detail.component';
import { EcomStoreMarkup } from 'app/shared/model/ecom-store-markup.model';

describe('Component Tests', () => {
  describe('EcomStoreMarkup Management Detail Component', () => {
    let comp: EcomStoreMarkupDetailComponent;
    let fixture: ComponentFixture<EcomStoreMarkupDetailComponent>;
    const route = ({ data: of({ ecomStoreMarkup: new EcomStoreMarkup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreMarkupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomStoreMarkupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStoreMarkupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomStoreMarkup on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomStoreMarkup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
