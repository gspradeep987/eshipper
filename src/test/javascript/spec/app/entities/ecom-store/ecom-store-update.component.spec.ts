import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreUpdateComponent } from 'app/entities/ecom-store/ecom-store-update.component';
import { EcomStoreService } from 'app/entities/ecom-store/ecom-store.service';
import { EcomStore } from 'app/shared/model/ecom-store.model';

describe('Component Tests', () => {
  describe('EcomStore Management Update Component', () => {
    let comp: EcomStoreUpdateComponent;
    let fixture: ComponentFixture<EcomStoreUpdateComponent>;
    let service: EcomStoreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomStoreUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomStore(123);
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
        const entity = new EcomStore();
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
