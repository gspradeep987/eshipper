import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { FranchiseUpdateComponent } from 'app/entities/franchise/franchise-update.component';
import { FranchiseService } from 'app/entities/franchise/franchise.service';
import { Franchise } from 'app/shared/model/franchise.model';

describe('Component Tests', () => {
  describe('Franchise Management Update Component', () => {
    let comp: FranchiseUpdateComponent;
    let fixture: ComponentFixture<FranchiseUpdateComponent>;
    let service: FranchiseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [FranchiseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FranchiseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FranchiseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FranchiseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Franchise(123);
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
        const entity = new Franchise();
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
