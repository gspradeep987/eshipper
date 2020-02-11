import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreAddressUpdateComponent } from 'app/entities/ecom-store-address/ecom-store-address-update.component';
import { EcomStoreAddressService } from 'app/entities/ecom-store-address/ecom-store-address.service';
import { EcomStoreAddress } from 'app/shared/model/ecom-store-address.model';

describe('Component Tests', () => {
  describe('EcomStoreAddress Management Update Component', () => {
    let comp: EcomStoreAddressUpdateComponent;
    let fixture: ComponentFixture<EcomStoreAddressUpdateComponent>;
    let service: EcomStoreAddressService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreAddressUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomStoreAddressUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreAddressUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreAddressService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomStoreAddress(123);
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
        const entity = new EcomStoreAddress();
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
