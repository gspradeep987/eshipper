import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreSyncComponent } from 'app/entities/ecom-store-sync/ecom-store-sync.component';
import { EcomStoreSyncService } from 'app/entities/ecom-store-sync/ecom-store-sync.service';
import { EcomStoreSync } from 'app/shared/model/ecom-store-sync.model';

describe('Component Tests', () => {
  describe('EcomStoreSync Management Component', () => {
    let comp: EcomStoreSyncComponent;
    let fixture: ComponentFixture<EcomStoreSyncComponent>;
    let service: EcomStoreSyncService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreSyncComponent]
      })
        .overrideTemplate(EcomStoreSyncComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreSyncComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreSyncService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomStoreSync(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomStoreSyncs && comp.ecomStoreSyncs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
