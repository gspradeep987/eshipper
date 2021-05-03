import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EcomStorePackageSettingsDetailComponent } from './ecom-store-package-settings-detail.component';

describe('Component Tests', () => {
  describe('EcomStorePackageSettings Management Detail Component', () => {
    let comp: EcomStorePackageSettingsDetailComponent;
    let fixture: ComponentFixture<EcomStorePackageSettingsDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [EcomStorePackageSettingsDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ ecomStorePackageSettings: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(EcomStorePackageSettingsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStorePackageSettingsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomStorePackageSettings on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomStorePackageSettings).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
