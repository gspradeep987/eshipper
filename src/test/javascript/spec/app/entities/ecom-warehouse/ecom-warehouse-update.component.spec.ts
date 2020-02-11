import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomWarehouseUpdateComponent } from 'app/entities/ecom-warehouse/ecom-warehouse-update.component';
import { EcomWarehouseService } from 'app/entities/ecom-warehouse/ecom-warehouse.service';
import { EcomWarehouse } from 'app/shared/model/ecom-warehouse.model';

describe('Component Tests', () => {
  describe('EcomWarehouse Management Update Component', () => {
    let comp: EcomWarehouseUpdateComponent;
    let fixture: ComponentFixture<EcomWarehouseUpdateComponent>;
    let service: EcomWarehouseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomWarehouseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomWarehouseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomWarehouseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomWarehouseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomWarehouse(123);
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
        const entity = new EcomWarehouse();
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
