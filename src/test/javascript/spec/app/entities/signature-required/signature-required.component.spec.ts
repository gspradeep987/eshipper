import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { SignatureRequiredComponent } from 'app/entities/signature-required/signature-required.component';
import { SignatureRequiredService } from 'app/entities/signature-required/signature-required.service';
import { SignatureRequired } from 'app/shared/model/signature-required.model';

describe('Component Tests', () => {
  describe('SignatureRequired Management Component', () => {
    let comp: SignatureRequiredComponent;
    let fixture: ComponentFixture<SignatureRequiredComponent>;
    let service: SignatureRequiredService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [SignatureRequiredComponent]
      })
        .overrideTemplate(SignatureRequiredComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SignatureRequiredComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SignatureRequiredService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SignatureRequired(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.signatureRequireds && comp.signatureRequireds[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
