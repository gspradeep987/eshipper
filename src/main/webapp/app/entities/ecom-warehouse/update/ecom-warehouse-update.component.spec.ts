jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomWarehouseService } from '../service/ecom-warehouse.service';
import { IEcomWarehouse, EcomWarehouse } from '../ecom-warehouse.model';

import { EcomWarehouseUpdateComponent } from './ecom-warehouse-update.component';

describe('Component Tests', () => {
  describe('EcomWarehouse Management Update Component', () => {
    let comp: EcomWarehouseUpdateComponent;
    let fixture: ComponentFixture<EcomWarehouseUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomWarehouseService: EcomWarehouseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomWarehouseUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomWarehouseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomWarehouseUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomWarehouseService = TestBed.inject(EcomWarehouseService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const ecomWarehouse: IEcomWarehouse = { id: 456 };

        activatedRoute.data = of({ ecomWarehouse });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomWarehouse));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomWarehouse = { id: 123 };
        spyOn(ecomWarehouseService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomWarehouse });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomWarehouse }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomWarehouseService.update).toHaveBeenCalledWith(ecomWarehouse);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomWarehouse = new EcomWarehouse();
        spyOn(ecomWarehouseService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomWarehouse });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomWarehouse }));
        saveSubject.complete();

        // THEN
        expect(ecomWarehouseService.create).toHaveBeenCalledWith(ecomWarehouse);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomWarehouse = { id: 123 };
        spyOn(ecomWarehouseService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomWarehouse });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomWarehouseService.update).toHaveBeenCalledWith(ecomWarehouse);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
