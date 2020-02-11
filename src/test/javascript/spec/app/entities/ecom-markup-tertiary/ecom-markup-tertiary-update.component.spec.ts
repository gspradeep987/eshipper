import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomMarkupTertiaryUpdateComponent } from 'app/entities/ecom-markup-tertiary/ecom-markup-tertiary-update.component';
import { EcomMarkupTertiaryService } from 'app/entities/ecom-markup-tertiary/ecom-markup-tertiary.service';
import { EcomMarkupTertiary } from 'app/shared/model/ecom-markup-tertiary.model';

describe('Component Tests', () => {
  describe('EcomMarkupTertiary Management Update Component', () => {
    let comp: EcomMarkupTertiaryUpdateComponent;
    let fixture: ComponentFixture<EcomMarkupTertiaryUpdateComponent>;
    let service: EcomMarkupTertiaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMarkupTertiaryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomMarkupTertiaryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupTertiaryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomMarkupTertiaryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomMarkupTertiary(123);
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
        const entity = new EcomMarkupTertiary();
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
