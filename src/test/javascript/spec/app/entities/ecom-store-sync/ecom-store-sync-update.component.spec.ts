import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreSyncUpdateComponent } from 'app/entities/ecom-store-sync/ecom-store-sync-update.component';
import { EcomStoreSyncService } from 'app/entities/ecom-store-sync/ecom-store-sync.service';
import { EcomStoreSync } from 'app/shared/model/ecom-store-sync.model';

describe('Component Tests', () => {
  describe('EcomStoreSync Management Update Component', () => {
    let comp: EcomStoreSyncUpdateComponent;
    let fixture: ComponentFixture<EcomStoreSyncUpdateComponent>;
    let service: EcomStoreSyncService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreSyncUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(EcomStoreSyncUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreSyncUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreSyncService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EcomStoreSync(123);
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
        const entity = new EcomStoreSync();
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
