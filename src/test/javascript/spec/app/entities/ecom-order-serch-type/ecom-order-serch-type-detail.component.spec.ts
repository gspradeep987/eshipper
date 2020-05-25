import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderSerchTypeDetailComponent } from 'app/entities/ecom-order-serch-type/ecom-order-serch-type-detail.component';
import { EcomOrderSerchType } from 'app/shared/model/ecom-order-serch-type.model';

describe('Component Tests', () => {
  describe('EcomOrderSerchType Management Detail Component', () => {
    let comp: EcomOrderSerchTypeDetailComponent;
    let fixture: ComponentFixture<EcomOrderSerchTypeDetailComponent>;
    const route = ({ data: of({ ecomOrderSerchType: new EcomOrderSerchType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderSerchTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EcomOrderSerchTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomOrderSerchTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomOrderSerchType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomOrderSerchType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
