import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { SalesAgentTypeDetailComponent } from 'app/entities/sales-agent-type/sales-agent-type-detail.component';
import { SalesAgentType } from 'app/shared/model/sales-agent-type.model';

describe('Component Tests', () => {
  describe('SalesAgentType Management Detail Component', () => {
    let comp: SalesAgentTypeDetailComponent;
    let fixture: ComponentFixture<SalesAgentTypeDetailComponent>;
    const route = ({ data: of({ salesAgentType: new SalesAgentType(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [SalesAgentTypeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SalesAgentTypeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SalesAgentTypeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load salesAgentType on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.salesAgentType).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
