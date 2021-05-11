import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { EcomStoreSyncService } from '../service/ecom-store-sync.service';

import { EcomStoreSyncComponent } from './ecom-store-sync.component';

describe('Component Tests', () => {
  describe('EcomStoreSync Management Component', () => {
    let comp: EcomStoreSyncComponent;
    let fixture: ComponentFixture<EcomStoreSyncComponent>;
    let service: EcomStoreSyncService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomStoreSyncComponent],
      })
        .overrideTemplate(EcomStoreSyncComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreSyncComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(EcomStoreSyncService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ id: 123 }],
            headers,
          })
        )
      );
    });

    it('Should call load all on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomStoreSyncs?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
