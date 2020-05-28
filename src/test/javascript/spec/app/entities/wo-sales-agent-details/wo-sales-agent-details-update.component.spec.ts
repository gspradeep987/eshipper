import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { WoSalesAgentDetailsUpdateComponent } from 'app/entities/wo-sales-agent-details/wo-sales-agent-details-update.component';
import { WoSalesAgentDetailsService } from 'app/entities/wo-sales-agent-details/wo-sales-agent-details.service';
import { WoSalesAgentDetails } from 'app/shared/model/wo-sales-agent-details.model';

describe('Component Tests', () => {
  describe('WoSalesAgentDetails Management Update Component', () => {
    let comp: WoSalesAgentDetailsUpdateComponent;
    let fixture: ComponentFixture<WoSalesAgentDetailsUpdateComponent>;
    let service: WoSalesAgentDetailsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [WoSalesAgentDetailsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(WoSalesAgentDetailsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WoSalesAgentDetailsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WoSalesAgentDetailsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WoSalesAgentDetails(123);
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
        const entity = new WoSalesAgentDetails();
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
