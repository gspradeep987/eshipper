import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreSyncDetailComponent } from 'app/entities/ecom-store-sync/ecom-store-sync-detail.component';
import { EcomStoreSync } from 'app/shared/model/ecom-store-sync.model';

describe('Component Tests', () => {
  describe('EcomStoreSync Management Detail Component', () => {
    let comp: EcomStoreSyncDetailComponent;
    let fixture: ComponentFixture<EcomStoreSyncDetailComponent>;
    const route = ({ data: of({ ecomStoreSync: new EcomStoreSync(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreSyncDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomStoreSyncDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStoreSyncDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomStoreSync on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomStoreSync).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
