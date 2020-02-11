import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomMailTemplateDetailComponent } from 'app/entities/ecom-mail-template/ecom-mail-template-detail.component';
import { EcomMailTemplate } from 'app/shared/model/ecom-mail-template.model';

describe('Component Tests', () => {
  describe('EcomMailTemplate Management Detail Component', () => {
    let comp: EcomMailTemplateDetailComponent;
    let fixture: ComponentFixture<EcomMailTemplateDetailComponent>;
    const route = ({ data: of({ ecomMailTemplate: new EcomMailTemplate(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomMailTemplateDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(EcomMailTemplateDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomMailTemplateDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomMailTemplate on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomMailTemplate).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
