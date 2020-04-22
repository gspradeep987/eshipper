import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { SignatureRequiredUpdateComponent } from 'app/entities/signature-required/signature-required-update.component';
import { SignatureRequiredService } from 'app/entities/signature-required/signature-required.service';
import { SignatureRequired } from 'app/shared/model/signature-required.model';

describe('Component Tests', () => {
  describe('SignatureRequired Management Update Component', () => {
    let comp: SignatureRequiredUpdateComponent;
    let fixture: ComponentFixture<SignatureRequiredUpdateComponent>;
    let service: SignatureRequiredService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [SignatureRequiredUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SignatureRequiredUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SignatureRequiredUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SignatureRequiredService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SignatureRequired(123);
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
        const entity = new SignatureRequired();
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
