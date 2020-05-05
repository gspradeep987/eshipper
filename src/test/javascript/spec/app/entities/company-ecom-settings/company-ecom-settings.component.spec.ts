import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { CompanyEcomSettingsComponent } from 'app/entities/company-ecom-settings/company-ecom-settings.component';
import { CompanyEcomSettingsService } from 'app/entities/company-ecom-settings/company-ecom-settings.service';
import { CompanyEcomSettings } from 'app/shared/model/company-ecom-settings.model';

describe('Component Tests', () => {
  describe('CompanyEcomSettings Management Component', () => {
    let comp: CompanyEcomSettingsComponent;
    let fixture: ComponentFixture<CompanyEcomSettingsComponent>;
    let service: CompanyEcomSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CompanyEcomSettingsComponent]
      })
        .overrideTemplate(CompanyEcomSettingsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CompanyEcomSettingsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CompanyEcomSettingsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CompanyEcomSettings(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.companyEcomSettings && comp.companyEcomSettings[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
