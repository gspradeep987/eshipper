import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomStoreSyncDetailComponent } from './ecom-store-sync-detail.component';

describe('Component Tests', () => {
  describe('EcomStoreSync Management Detail Component', () => {
    let comp: EcomStoreSyncDetailComponent;
    let fixture: ComponentFixture<EcomStoreSyncDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomStoreSyncDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomStoreSync: { id: 123 } }) },
          },
        ],
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
