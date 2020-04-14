import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CompanyCarrierAccountUpdateComponent } from 'app/entities/company-carrier-account/company-carrier-account-update.component';
import { CompanyCarrierAccountService } from 'app/entities/company-carrier-account/company-carrier-account.service';
import { CompanyCarrierAccount } from 'app/shared/model/company-carrier-account.model';

describe('Component Tests', () => {
  describe('CompanyCarrierAccount Management Update Component', () => {
    let comp: CompanyCarrierAccountUpdateComponent;
    let fixture: ComponentFixture<CompanyCarrierAccountUpdateComponent>;
    let service: CompanyCarrierAccountService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CompanyCarrierAccountUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CompanyCarrierAccountUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CompanyCarrierAccountUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CompanyCarrierAccountService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CompanyCarrierAccount(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new CompanyCarrierAccount();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
