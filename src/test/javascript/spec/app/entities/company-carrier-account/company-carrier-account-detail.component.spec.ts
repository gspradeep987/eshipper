import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CompanyCarrierAccountDetailComponent } from 'app/entities/company-carrier-account/company-carrier-account-detail.component';
import { CompanyCarrierAccount } from 'app/shared/model/company-carrier-account.model';

describe('Component Tests', () => {
  describe('CompanyCarrierAccount Management Detail Component', () => {
    let comp: CompanyCarrierAccountDetailComponent;
    let fixture: ComponentFixture<CompanyCarrierAccountDetailComponent>;
    const route = ({ data: of({ companyCarrierAccount: new CompanyCarrierAccount(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CompanyCarrierAccountDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(CompanyCarrierAccountDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(CompanyCarrierAccountDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load companyCarrierAccount on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.companyCarrierAccount).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
