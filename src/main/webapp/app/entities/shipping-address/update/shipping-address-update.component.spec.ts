jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { ShippingAddressService } from '../service/shipping-address.service';
import { IShippingAddress, ShippingAddress } from '../shipping-address.model';

import { ShippingAddressUpdateComponent } from './shipping-address-update.component';

describe('Component Tests', () => {
  describe('ShippingAddress Management Update Component', () => {
    let comp: ShippingAddressUpdateComponent;
    let fixture: ComponentFixture<ShippingAddressUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let shippingAddressService: ShippingAddressService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [ShippingAddressUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(ShippingAddressUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ShippingAddressUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      shippingAddressService = TestBed.inject(ShippingAddressService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const shippingAddress: IShippingAddress = { id: 456 };

        activatedRoute.data = of({ shippingAddress });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(shippingAddress));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const shippingAddress = { id: 123 };
        spyOn(shippingAddressService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ shippingAddress });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: shippingAddress }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(shippingAddressService.update).toHaveBeenCalledWith(shippingAddress);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const shippingAddress = new ShippingAddress();
        spyOn(shippingAddressService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ shippingAddress });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: shippingAddress }));
        saveSubject.complete();

        // THEN
        expect(shippingAddressService.create).toHaveBeenCalledWith(shippingAddress);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const shippingAddress = { id: 123 };
        spyOn(shippingAddressService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ shippingAddress });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(shippingAddressService.update).toHaveBeenCalledWith(shippingAddress);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
