import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreImagesComponent } from 'app/entities/ecom-store-images/ecom-store-images.component';
import { EcomStoreImagesService } from 'app/entities/ecom-store-images/ecom-store-images.service';
import { EcomStoreImages } from 'app/shared/model/ecom-store-images.model';

describe('Component Tests', () => {
  describe('EcomStoreImages Management Component', () => {
    let comp: EcomStoreImagesComponent;
    let fixture: ComponentFixture<EcomStoreImagesComponent>;
    let service: EcomStoreImagesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreImagesComponent],
      })
        .overrideTemplate(EcomStoreImagesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomStoreImagesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomStoreImagesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomStoreImages(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomStoreImages && comp.ecomStoreImages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
