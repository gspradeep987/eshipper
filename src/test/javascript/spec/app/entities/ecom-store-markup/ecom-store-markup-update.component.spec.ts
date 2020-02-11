import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreMarkupUpdateComponent } from 'app/entities/ecom-store-markup/ecom-store-markup-update.component';
import { EcomStoreMarkupService } from 'app/entities/ecom-store-markup/ecom-store-markup.service';
import { EcomStoreMarkup } from 'app/shared/model/ecom-store-markup.model';

describe('Component Tests', () => {
  describe('EcomStoreMarkup Management Update Component', () => {
    let comp: EcomStoreMarkupUpdateComponent;
    let fixture: ComponentFixture<EcomStoreMarkupUpdateComponent>;
    let service: EcomStoreMarkupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreMarkupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomStoreMarkupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreMarkupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreMarkupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomStoreMarkup(123);
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
        const entity = new EcomStoreMarkup();
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
