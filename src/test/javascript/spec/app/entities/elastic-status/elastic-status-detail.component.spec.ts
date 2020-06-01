import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { ElasticStatusDetailComponent } from 'app/entities/elastic-status/elastic-status-detail.component';
import { ElasticStatus } from 'app/shared/model/elastic-status.model';

describe('Component Tests', () => {
  describe('ElasticStatus Management Detail Component', () => {
    let comp: ElasticStatusDetailComponent;
    let fixture: ComponentFixture<ElasticStatusDetailComponent>;
    const route = ({ data: of({ elasticStatus: new ElasticStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [ElasticStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ElasticStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ElasticStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load elasticStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.elasticStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
