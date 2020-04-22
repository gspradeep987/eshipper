import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CompanyEcomSettingsDetailComponent } from 'app/entities/company-ecom-settings/company-ecom-settings-detail.component';
import { CompanyEcomSettings } from 'app/shared/model/company-ecom-settings.model';

describe('Component Tests', () => {
  describe('CompanyEcomSettings Management Detail Component', () => {
    let comp: CompanyEcomSettingsDetailComponent;
    let fixture: ComponentFixture<CompanyEcomSettingsDetailComponent>;
    const route = ({ data: of({ companyEcomSettings: new CompanyEcomSettings(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CompanyEcomSettingsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CompanyEcomSettingsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CompanyEcomSettingsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load companyEcomSettings on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.companyEcomSettings).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
