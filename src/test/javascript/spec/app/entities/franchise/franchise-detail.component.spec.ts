import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { FranchiseDetailComponent } from 'app/entities/franchise/franchise-detail.component';
import { Franchise } from 'app/shared/model/franchise.model';

describe('Component Tests', () => {
  describe('Franchise Management Detail Component', () => {
    let comp: FranchiseDetailComponent;
    let fixture: ComponentFixture<FranchiseDetailComponent>;
    const route = ({ data: of({ franchise: new Franchise(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [FranchiseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FranchiseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FranchiseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load franchise on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.franchise).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
