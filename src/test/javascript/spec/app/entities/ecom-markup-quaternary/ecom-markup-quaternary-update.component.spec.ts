import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomMarkupQuaternaryUpdateComponent } from 'app/entities/ecom-markup-quaternary/ecom-markup-quaternary-update.component';
import { EcomMarkupQuaternaryService } from 'app/entities/ecom-markup-quaternary/ecom-markup-quaternary.service';
import { EcomMarkupQuaternary } from 'app/shared/model/ecom-markup-quaternary.model';

describe('Component Tests', () => {
  describe('EcomMarkupQuaternary Management Update Component', () => {
    let comp: EcomMarkupQuaternaryUpdateComponent;
    let fixture: ComponentFixture<EcomMarkupQuaternaryUpdateComponent>;
    let service: EcomMarkupQuaternaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMarkupQuaternaryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomMarkupQuaternaryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupQuaternaryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomMarkupQuaternaryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomMarkupQuaternary(123);
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
        const entity = new EcomMarkupQuaternary();
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
