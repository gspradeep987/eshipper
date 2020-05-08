import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomorderAttachmentUpdateComponent } from 'app/entities/ecomorder-attachment/ecomorder-attachment-update.component';
import { EcomorderAttachmentService } from 'app/entities/ecomorder-attachment/ecomorder-attachment.service';
import { EcomorderAttachment } from 'app/shared/model/ecomorder-attachment.model';

describe('Component Tests', () => {
  describe('EcomorderAttachment Management Update Component', () => {
    let comp: EcomorderAttachmentUpdateComponent;
    let fixture: ComponentFixture<EcomorderAttachmentUpdateComponent>;
    let service: EcomorderAttachmentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomorderAttachmentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomorderAttachmentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomorderAttachmentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomorderAttachmentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomorderAttachment(123);
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
        const entity = new EcomorderAttachment();
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
