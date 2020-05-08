import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomOrderAttachmentComponent } from 'app/entities/ecom-order-attachment/ecom-order-attachment.component';
import { EcomOrderAttachmentService } from 'app/entities/ecom-order-attachment/ecom-order-attachment.service';
import { EcomOrderAttachment } from 'app/shared/model/ecom-order-attachment.model';

describe('Component Tests', () => {
  describe('EcomOrderAttachment Management Component', () => {
    let comp: EcomOrderAttachmentComponent;
    let fixture: ComponentFixture<EcomOrderAttachmentComponent>;
    let service: EcomOrderAttachmentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomOrderAttachmentComponent]
      })
        .overrideTemplate(EcomOrderAttachmentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomOrderAttachmentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomOrderAttachmentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomOrderAttachment(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomOrderAttachments && comp.ecomOrderAttachments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
