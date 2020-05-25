import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderFullfillmentStatusDetailComponent } from 'app/entities/ecom-order-fullfillment-status/ecom-order-fullfillment-status-detail.component';
import { EcomOrderFullfillmentStatus } from 'app/shared/model/ecom-order-fullfillment-status.model';

describe('Component Tests', () => {
  describe('EcomOrderFullfillmentStatus Management Detail Component', () => {
    let comp: EcomOrderFullfillmentStatusDetailComponent;
    let fixture: ComponentFixture<EcomOrderFullfillmentStatusDetailComponent>;
    const route = ({ data: of({ ecomOrderFullfillmentStatus: new EcomOrderFullfillmentStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderFullfillmentStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EcomOrderFullfillmentStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomOrderFullfillmentStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomOrderFullfillmentStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomOrderFullfillmentStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
