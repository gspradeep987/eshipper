import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomStoreImagesDetailComponent } from 'app/entities/ecom-store-images/ecom-store-images-detail.component';
import { EcomStoreImages } from 'app/shared/model/ecom-store-images.model';

describe('Component Tests', () => {
  describe('EcomStoreImages Management Detail Component', () => {
    let comp: EcomStoreImagesDetailComponent;
    let fixture: ComponentFixture<EcomStoreImagesDetailComponent>;
    const route = ({ data: of({ ecomStoreImages: new EcomStoreImages(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomStoreImagesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EcomStoreImagesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomStoreImagesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomStoreImages on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomStoreImages).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
