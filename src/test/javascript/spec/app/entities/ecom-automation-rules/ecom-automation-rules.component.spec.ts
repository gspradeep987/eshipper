import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { EcomAutomationRulesComponent } from 'app/entities/ecom-automation-rules/ecom-automation-rules.component';
import { EcomAutomationRulesService } from 'app/entities/ecom-automation-rules/ecom-automation-rules.service';
import { EcomAutomationRules } from 'app/shared/model/ecom-automation-rules.model';

describe('Component Tests', () => {
  describe('EcomAutomationRules Management Component', () => {
    let comp: EcomAutomationRulesComponent;
    let fixture: ComponentFixture<EcomAutomationRulesComponent>;
    let service: EcomAutomationRulesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [EcomAutomationRulesComponent],
      })
        .overrideTemplate(EcomAutomationRulesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EcomAutomationRulesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EcomAutomationRulesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new EcomAutomationRules(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.ecomAutomationRules && comp.ecomAutomationRules[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
