import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { EcomAutomationRulesDetailComponent } from 'app/entities/ecom-automation-rules/ecom-automation-rules-detail.component';
import { EcomAutomationRules } from 'app/shared/model/ecom-automation-rules.model';

describe('Component Tests', () => {
  describe('EcomAutomationRules Management Detail Component', () => {
    let comp: EcomAutomationRulesDetailComponent;
    let fixture: ComponentFixture<EcomAutomationRulesDetailComponent>;
    const route = ({ data: of({ ecomAutomationRules: new EcomAutomationRules(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomAutomationRulesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(EcomAutomationRulesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(EcomAutomationRulesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ecomAutomationRules on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ecomAutomationRules).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
