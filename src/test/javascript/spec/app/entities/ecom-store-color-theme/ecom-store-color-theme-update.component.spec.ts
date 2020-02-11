import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreColorThemeUpdateComponent } from 'app/entities/ecom-store-color-theme/ecom-store-color-theme-update.component';
import { EcomStoreColorThemeService } from 'app/entities/ecom-store-color-theme/ecom-store-color-theme.service';
import { EcomStoreColorTheme } from 'app/shared/model/ecom-store-color-theme.model';

describe('Component Tests', () => {
  describe('EcomStoreColorTheme Management Update Component', () => {
    let comp: EcomStoreColorThemeUpdateComponent;
    let fixture: ComponentFixture<EcomStoreColorThemeUpdateComponent>;
    let service: EcomStoreColorThemeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreColorThemeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomStoreColorThemeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreColorThemeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreColorThemeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomStoreColorTheme(123);
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
        const entity = new EcomStoreColorTheme();
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
