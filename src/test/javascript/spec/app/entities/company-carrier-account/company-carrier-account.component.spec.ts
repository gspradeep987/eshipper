import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { CompanyCarrierAccountComponent } from 'app/entities/company-carrier-account/company-carrier-account.component';
import { CompanyCarrierAccountService } from 'app/entities/company-carrier-account/company-carrier-account.service';
import { CompanyCarrierAccount } from 'app/shared/model/company-carrier-account.model';

describe('Component Tests', () => {
  describe('CompanyCarrierAccount Management Component', () => {
    let comp: CompanyCarrierAccountComponent;
    let fixture: ComponentFixture<CompanyCarrierAccountComponent>;
    let service: CompanyCarrierAccountService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CompanyCarrierAccountComponent]
      })
        .overrideTemplate(CompanyCarrierAccountComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CompanyCarrierAccountComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CompanyCarrierAccountService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CompanyCarrierAccount(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.companyCarrierAccounts && comp.companyCarrierAccounts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
