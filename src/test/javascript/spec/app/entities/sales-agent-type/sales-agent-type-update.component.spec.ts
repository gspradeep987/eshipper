import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { SalesAgentTypeUpdateComponent } from 'app/entities/sales-agent-type/sales-agent-type-update.component';
import { SalesAgentTypeService } from 'app/entities/sales-agent-type/sales-agent-type.service';
import { SalesAgentType } from 'app/shared/model/sales-agent-type.model';

describe('Component Tests', () => {
  describe('SalesAgentType Management Update Component', () => {
    let comp: SalesAgentTypeUpdateComponent;
    let fixture: ComponentFixture<SalesAgentTypeUpdateComponent>;
    let service: SalesAgentTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [SalesAgentTypeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SalesAgentTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SalesAgentTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SalesAgentTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SalesAgentType(123);
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
        const entity = new SalesAgentType();
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
