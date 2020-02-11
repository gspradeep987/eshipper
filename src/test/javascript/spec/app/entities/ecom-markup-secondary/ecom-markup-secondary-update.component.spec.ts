import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomMarkupSecondaryUpdateComponent } from 'app/entities/ecom-markup-secondary/ecom-markup-secondary-update.component';
import { EcomMarkupSecondaryService } from 'app/entities/ecom-markup-secondary/ecom-markup-secondary.service';
import { EcomMarkupSecondary } from 'app/shared/model/ecom-markup-secondary.model';

describe('Component Tests', () => {
  describe('EcomMarkupSecondary Management Update Component', () => {
    let comp: EcomMarkupSecondaryUpdateComponent;
    let fixture: ComponentFixture<EcomMarkupSecondaryUpdateComponent>;
    let service: EcomMarkupSecondaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMarkupSecondaryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomMarkupSecondaryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupSecondaryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomMarkupSecondaryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomMarkupSecondary(123);
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
        const entity = new EcomMarkupSecondary();
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
