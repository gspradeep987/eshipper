import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesAgentUpdateComponent } from 'app/entities/wo-sales-agent/wo-sales-agent-update.component';
import { WoSalesAgentService } from 'app/entities/wo-sales-agent/wo-sales-agent.service';
import { WoSalesAgent } from 'app/shared/model/wo-sales-agent.model';

describe('Component Tests', () => {
  describe('WoSalesAgent Management Update Component', () => {
    let comp: WoSalesAgentUpdateComponent;
    let fixture: ComponentFixture<WoSalesAgentUpdateComponent>;
    let service: WoSalesAgentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesAgentUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(WoSalesAgentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoSalesAgentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoSalesAgentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoSalesAgent(123);
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
        const entity = new WoSalesAgent();
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
