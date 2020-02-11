import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomMarkupPrimaryUpdateComponent } from 'app/entities/ecom-markup-primary/ecom-markup-primary-update.component';
import { EcomMarkupPrimaryService } from 'app/entities/ecom-markup-primary/ecom-markup-primary.service';
import { EcomMarkupPrimary } from 'app/shared/model/ecom-markup-primary.model';

describe('Component Tests', () => {
  describe('EcomMarkupPrimary Management Update Component', () => {
    let comp: EcomMarkupPrimaryUpdateComponent;
    let fixture: ComponentFixture<EcomMarkupPrimaryUpdateComponent>;
    let service: EcomMarkupPrimaryService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMarkupPrimaryUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomMarkupPrimaryUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomMarkupPrimaryUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomMarkupPrimaryService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomMarkupPrimary(123);
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
        const entity = new EcomMarkupPrimary();
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
