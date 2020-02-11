import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreDetailComponent } from 'app/entities/ecom-store/ecom-store-detail.component';
import { EcomStore } from 'app/shared/model/ecom-store.model';

describe('Component Tests', () => {
  describe('EcomStore Management Detail Component', () => {
    let comp: EcomStoreDetailComponent;
    let fixture: ComponentFixture<EcomStoreDetailComponent>;
    const route = ({ data: of({ ecomStore: new EcomStore(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomStoreDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStoreDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomStore on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomStore).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
