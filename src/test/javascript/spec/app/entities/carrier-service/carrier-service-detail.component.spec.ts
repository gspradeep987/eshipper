import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CarrierServiceDetailComponent } from 'app/entities/carrier-service/carrier-service-detail.component';
import { CarrierService } from 'app/shared/model/carrier-service.model';

describe('Component Tests', () => {
  describe('CarrierService Management Detail Component', () => {
    let comp: CarrierServiceDetailComponent;
    let fixture: ComponentFixture<CarrierServiceDetailComponent>;
    const route = ({ data: of({ carrierService: new CarrierService(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CarrierServiceDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(CarrierServiceDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CarrierServiceDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load carrierService on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.carrierService).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
