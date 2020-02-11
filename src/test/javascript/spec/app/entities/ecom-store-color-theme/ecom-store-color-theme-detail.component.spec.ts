import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreColorThemeDetailComponent } from 'app/entities/ecom-store-color-theme/ecom-store-color-theme-detail.component';
import { EcomStoreColorTheme } from 'app/shared/model/ecom-store-color-theme.model';

describe('Component Tests', () => {
  describe('EcomStoreColorTheme Management Detail Component', () => {
    let comp: EcomStoreColorThemeDetailComponent;
    let fixture: ComponentFixture<EcomStoreColorThemeDetailComponent>;
    const route = ({ data: of({ ecomStoreColorTheme: new EcomStoreColorTheme(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreColorThemeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomStoreColorThemeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStoreColorThemeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomStoreColorTheme on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomStoreColorTheme).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
