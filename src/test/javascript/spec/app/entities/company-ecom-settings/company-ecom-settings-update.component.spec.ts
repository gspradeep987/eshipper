import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { CompanyEcomSettingsUpdateComponent } from 'app/entities/company-ecom-settings/company-ecom-settings-update.component';
import { CompanyEcomSettingsService } from 'app/entities/company-ecom-settings/company-ecom-settings.service';
import { CompanyEcomSettings } from 'app/shared/model/company-ecom-settings.model';

describe('Component Tests', () => {
  describe('CompanyEcomSettings Management Update Component', () => {
    let comp: CompanyEcomSettingsUpdateComponent;
    let fixture: ComponentFixture<CompanyEcomSettingsUpdateComponent>;
    let service: CompanyEcomSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [CompanyEcomSettingsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(CompanyEcomSettingsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CompanyEcomSettingsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CompanyEcomSettingsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CompanyEcomSettings(123);
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
        const entity = new CompanyEcomSettings();
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
