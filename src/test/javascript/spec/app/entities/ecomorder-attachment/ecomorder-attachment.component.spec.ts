import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomorderAttachmentComponent } from 'app/entities/ecomorder-attachment/ecomorder-attachment.component';
import { EcomorderAttachmentService } from 'app/entities/ecomorder-attachment/ecomorder-attachment.service';
import { EcomorderAttachment } from 'app/shared/model/ecomorder-attachment.model';

describe('Component Tests', () => {
  describe('EcomorderAttachment Management Component', () => {
    let comp: EcomorderAttachmentComponent;
    let fixture: ComponentFixture<EcomorderAttachmentComponent>;
    let service: EcomorderAttachmentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomorderAttachmentComponent]
      })
        .overrideTemplate(EcomorderAttachmentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomorderAttachmentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomorderAttachmentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomorderAttachment(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomorderAttachments && comp.ecomorderAttachments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
