import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderSerchTypeUpdateComponent } from 'app/entities/ecom-order-serch-type/ecom-order-serch-type-update.component';
import { EcomOrderSerchTypeService } from 'app/entities/ecom-order-serch-type/ecom-order-serch-type.service';
import { EcomOrderSerchType } from 'app/shared/model/ecom-order-serch-type.model';

describe('Component Tests', () => {
  describe('EcomOrderSerchType Management Update Component', () => {
    let comp: EcomOrderSerchTypeUpdateComponent;
    let fixture: ComponentFixture<EcomOrderSerchTypeUpdateComponent>;
    let service: EcomOrderSerchTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderSerchTypeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EcomOrderSerchTypeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomOrderSerchTypeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomOrderSerchTypeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomOrderSerchType(123);
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
        const entity = new EcomOrderSerchType();
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
