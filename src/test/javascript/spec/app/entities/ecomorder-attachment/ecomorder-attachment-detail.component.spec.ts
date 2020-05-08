import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomorderAttachmentDetailComponent } from 'app/entities/ecomorder-attachment/ecomorder-attachment-detail.component';
import { EcomorderAttachment } from 'app/shared/model/ecomorder-attachment.model';

describe('Component Tests', () => {
  describe('EcomorderAttachment Management Detail Component', () => {
    let comp: EcomorderAttachmentDetailComponent;
    let fixture: ComponentFixture<EcomorderAttachmentDetailComponent>;
    const route = ({ data: of({ ecomorderAttachment: new EcomorderAttachment(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomorderAttachmentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomorderAttachmentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomorderAttachmentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomorderAttachment on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomorderAttachment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
