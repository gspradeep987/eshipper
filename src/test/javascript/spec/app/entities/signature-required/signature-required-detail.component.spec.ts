import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { SignatureRequiredDetailComponent } from 'app/entities/signature-required/signature-required-detail.component';
import { SignatureRequired } from 'app/shared/model/signature-required.model';

describe('Component Tests', () => {
  describe('SignatureRequired Management Detail Component', () => {
    let comp: SignatureRequiredDetailComponent;
    let fixture: ComponentFixture<SignatureRequiredDetailComponent>;
    const route = ({ data: of({ signatureRequired: new SignatureRequired(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [SignatureRequiredDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SignatureRequiredDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SignatureRequiredDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load signatureRequired on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.signatureRequired).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
