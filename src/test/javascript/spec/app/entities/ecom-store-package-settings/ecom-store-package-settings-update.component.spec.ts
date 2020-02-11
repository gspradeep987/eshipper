import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStorePackageSettingsUpdateComponent } from 'app/entities/ecom-store-package-settings/ecom-store-package-settings-update.component';
import { EcomStorePackageSettingsService } from 'app/entities/ecom-store-package-settings/ecom-store-package-settings.service';
import { EcomStorePackageSettings } from 'app/shared/model/ecom-store-package-settings.model';

describe('Component Tests', () => {
  describe('EcomStorePackageSettings Management Update Component', () => {
    let comp: EcomStorePackageSettingsUpdateComponent;
    let fixture: ComponentFixture<EcomStorePackageSettingsUpdateComponent>;
    let service: EcomStorePackageSettingsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStorePackageSettingsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomStorePackageSettingsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStorePackageSettingsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStorePackageSettingsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomStorePackageSettings(123);
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
        const entity = new EcomStorePackageSettings();
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
