import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomProductImageUpdateComponent } from 'app/entities/ecom-product-image/ecom-product-image-update.component';
import { EcomProductImageService } from 'app/entities/ecom-product-image/ecom-product-image.service';
import { EcomProductImage } from 'app/shared/model/ecom-product-image.model';

describe('Component Tests', () => {
  describe('EcomProductImage Management Update Component', () => {
    let comp: EcomProductImageUpdateComponent;
    let fixture: ComponentFixture<EcomProductImageUpdateComponent>;
    let service: EcomProductImageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomProductImageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomProductImageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomProductImageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomProductImageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomProductImage(123);
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
        const entity = new EcomProductImage();
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
