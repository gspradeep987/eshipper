jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { ShipmentServiceService } from '../service/shipment-service.service';
import { IShipmentService, ShipmentService } from '../shipment-service.model';

import { ShipmentServiceUpdateComponent } from './shipment-service-update.component';

describe('Component Tests', () => {
  describe('ShipmentService Management Update Component', () => {
    let comp: ShipmentServiceUpdateComponent;
    let fixture: ComponentFixture<ShipmentServiceUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let shipmentServiceService: ShipmentServiceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [ShipmentServiceUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(ShipmentServiceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShipmentServiceUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      shipmentServiceService = TestBed.inject(ShipmentServiceService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const shipmentService: IShipmentService = { id: 456 };

        activatedRoute.data = of({ shipmentService });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(shipmentService));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const shipmentService = { id: 123 };
        spyOn(shipmentServiceService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ shipmentService });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: shipmentService }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(shipmentServiceService.update).toHaveBeenCalledWith(shipmentService);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const shipmentService = new ShipmentService();
        spyOn(shipmentServiceService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ shipmentService });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: shipmentService }));
        saveSubject.complete();

        // THEN
        expect(shipmentServiceService.create).toHaveBeenCalledWith(shipmentService);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const shipmentService = { id: 123 };
        spyOn(shipmentServiceService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ shipmentService });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(shipmentServiceService.update).toHaveBeenCalledWith(shipmentService);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
