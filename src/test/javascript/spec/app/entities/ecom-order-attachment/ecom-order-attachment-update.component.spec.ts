import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderAttachmentUpdateComponent } from 'app/entities/ecom-order-attachment/ecom-order-attachment-update.component';
import { EcomOrderAttachmentService } from 'app/entities/ecom-order-attachment/ecom-order-attachment.service';
import { EcomOrderAttachment } from 'app/shared/model/ecom-order-attachment.model';

describe('Component Tests', () => {
  describe('EcomOrderAttachment Management Update Component', () => {
    let comp: EcomOrderAttachmentUpdateComponent;
    let fixture: ComponentFixture<EcomOrderAttachmentUpdateComponent>;
    let service: EcomOrderAttachmentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderAttachmentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomOrderAttachmentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomOrderAttachmentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomOrderAttachmentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomOrderAttachment(123);
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
        const entity = new EcomOrderAttachment();
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
