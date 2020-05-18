import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomAutomationRulesUpdateComponent } from 'app/entities/ecom-automation-rules/ecom-automation-rules-update.component';
import { EcomAutomationRulesService } from 'app/entities/ecom-automation-rules/ecom-automation-rules.service';
import { EcomAutomationRules } from 'app/shared/model/ecom-automation-rules.model';

describe('Component Tests', () => {
  describe('EcomAutomationRules Management Update Component', () => {
    let comp: EcomAutomationRulesUpdateComponent;
    let fixture: ComponentFixture<EcomAutomationRulesUpdateComponent>;
    let service: EcomAutomationRulesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomAutomationRulesUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EcomAutomationRulesUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomAutomationRulesUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomAutomationRulesService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomAutomationRules(123);
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
        const entity = new EcomAutomationRules();
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
