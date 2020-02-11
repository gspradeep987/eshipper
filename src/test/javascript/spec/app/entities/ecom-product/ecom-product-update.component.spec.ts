import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomProductUpdateComponent } from 'app/entities/ecom-product/ecom-product-update.component';
import { EcomProductService } from 'app/entities/ecom-product/ecom-product.service';
import { EcomProduct } from 'app/shared/model/ecom-product.model';

describe('Component Tests', () => {
  describe('EcomProduct Management Update Component', () => {
    let comp: EcomProductUpdateComponent;
    let fixture: ComponentFixture<EcomProductUpdateComponent>;
    let service: EcomProductService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomProductUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomProductUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomProductUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomProductService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomProduct(123);
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
        const entity = new EcomProduct();
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
