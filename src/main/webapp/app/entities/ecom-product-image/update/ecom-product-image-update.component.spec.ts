jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { EcomProductImageService } from '../service/ecom-product-image.service';
import { IEcomProductImage, EcomProductImage } from '../ecom-product-image.model';
import { IEcomProduct } from 'app/entities/ecom-product/ecom-product.model';
import { EcomProductService } from 'app/entities/ecom-product/service/ecom-product.service';

import { EcomProductImageUpdateComponent } from './ecom-product-image-update.component';

describe('Component Tests', () => {
  describe('EcomProductImage Management Update Component', () => {
    let comp: EcomProductImageUpdateComponent;
    let fixture: ComponentFixture<EcomProductImageUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let ecomProductImageService: EcomProductImageService;
    let ecomProductService: EcomProductService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [EcomProductImageUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(EcomProductImageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomProductImageUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      ecomProductImageService = TestBed.inject(EcomProductImageService);
      ecomProductService = TestBed.inject(EcomProductService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should call EcomProduct query and add missing value', () => {
        const ecomProductImage: IEcomProductImage = { id: 456 };
        const ecomProduct: IEcomProduct = { id: 79881 };
        ecomProductImage.ecomProduct = ecomProduct;

        const ecomProductCollection: IEcomProduct[] = [{ id: 53868 }];
        spyOn(ecomProductService, 'query').and.returnValue(of(new HttpResponse({ body: ecomProductCollection })));
        const additionalEcomProducts = [ecomProduct];
        const expectedCollection: IEcomProduct[] = [...additionalEcomProducts, ...ecomProductCollection];
        spyOn(ecomProductService, 'addEcomProductToCollectionIfMissing').and.returnValue(expectedCollection);

        activatedRoute.data = of({ ecomProductImage });
        comp.ngOnInit();

        expect(ecomProductService.query).toHaveBeenCalled();
        expect(ecomProductService.addEcomProductToCollectionIfMissing).toHaveBeenCalledWith(
          ecomProductCollection,
          ...additionalEcomProducts
        );
        expect(comp.ecomProductsSharedCollection).toEqual(expectedCollection);
      });

      it('Should update editForm', () => {
        const ecomProductImage: IEcomProductImage = { id: 456 };
        const ecomProduct: IEcomProduct = { id: 19671 };
        ecomProductImage.ecomProduct = ecomProduct;

        activatedRoute.data = of({ ecomProductImage });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(ecomProductImage));
        expect(comp.ecomProductsSharedCollection).toContain(ecomProduct);
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomProductImage = { id: 123 };
        spyOn(ecomProductImageService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomProductImage });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomProductImage }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(ecomProductImageService.update).toHaveBeenCalledWith(ecomProductImage);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomProductImage = new EcomProductImage();
        spyOn(ecomProductImageService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomProductImage });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: ecomProductImage }));
        saveSubject.complete();

        // THEN
        expect(ecomProductImageService.create).toHaveBeenCalledWith(ecomProductImage);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const ecomProductImage = { id: 123 };
        spyOn(ecomProductImageService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ ecomProductImage });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(ecomProductImageService.update).toHaveBeenCalledWith(ecomProductImage);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });

    describe('Tracking relationships identifiers', () => {
      describe('trackEcomProductById', () => {
        it('Should return tracked EcomProduct primary key', () => {
          const entity = { id: 123 };
          const trackResult = comp.trackEcomProductById(0, entity);
          expect(trackResult).toEqual(entity.id);
        });
      });
    });
  });
});
