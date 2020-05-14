import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { User10UpdateComponent } from 'app/entities/user-10/user-10-update.component';
import { User10Service } from 'app/entities/user-10/user-10.service';
import { User10 } from 'app/shared/model/user-10.model';

describe('Component Tests', () => {
  describe('User10 Management Update Component', () => {
    let comp: User10UpdateComponent;
    let fixture: ComponentFixture<User10UpdateComponent>;
    let service: User10Service;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [User10UpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(User10UpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(User10UpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(User10Service);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new User10(123);
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
        const entity = new User10();
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
