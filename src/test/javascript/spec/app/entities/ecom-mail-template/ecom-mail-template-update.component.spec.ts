import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomMailTemplateUpdateComponent } from 'app/entities/ecom-mail-template/ecom-mail-template-update.component';
import { EcomMailTemplateService } from 'app/entities/ecom-mail-template/ecom-mail-template.service';
import { EcomMailTemplate } from 'app/shared/model/ecom-mail-template.model';

describe('Component Tests', () => {
  describe('EcomMailTemplate Management Update Component', () => {
    let comp: EcomMailTemplateUpdateComponent;
    let fixture: ComponentFixture<EcomMailTemplateUpdateComponent>;
    let service: EcomMailTemplateService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMailTemplateUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomMailTemplateUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMailTemplateUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomMailTemplateService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomMailTemplate(123);
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
        const entity = new EcomMailTemplate();
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
