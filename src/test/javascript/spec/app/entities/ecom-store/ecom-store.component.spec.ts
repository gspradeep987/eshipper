import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreComponent } from 'app/entities/ecom-store/ecom-store.component';
import { EcomStoreService } from 'app/entities/ecom-store/ecom-store.service';
import { EcomStore } from 'app/shared/model/ecom-store.model';

describe('Component Tests', () => {
  describe('EcomStore Management Component', () => {
    let comp: EcomStoreComponent;
    let fixture: ComponentFixture<EcomStoreComponent>;
    let service: EcomStoreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreComponent],
      })
        .overrideTemplate(EcomStoreComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomStore(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomStores && comp.ecomStores[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
