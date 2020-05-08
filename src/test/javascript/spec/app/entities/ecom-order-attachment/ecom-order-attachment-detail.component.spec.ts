import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderAttachmentDetailComponent } from 'app/entities/ecom-order-attachment/ecom-order-attachment-detail.component';
import { EcomOrderAttachment } from 'app/shared/model/ecom-order-attachment.model';

describe('Component Tests', () => {
  describe('EcomOrderAttachment Management Detail Component', () => {
    let comp: EcomOrderAttachmentDetailComponent;
    let fixture: ComponentFixture<EcomOrderAttachmentDetailComponent>;
    const route = ({ data: of({ ecomOrderAttachment: new EcomOrderAttachment(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderAttachmentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomOrderAttachmentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomOrderAttachmentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomOrderAttachment on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomOrderAttachment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
