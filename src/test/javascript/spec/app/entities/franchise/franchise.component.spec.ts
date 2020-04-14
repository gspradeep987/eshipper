import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EshipperTestModule } from '../../../test.module';
import { FranchiseComponent } from 'app/entities/franchise/franchise.component';
import { FranchiseService } from 'app/entities/franchise/franchise.service';
import { Franchise } from 'app/shared/model/franchise.model';

describe('Component Tests', () => {
  describe('Franchise Management Component', () => {
    let comp: FranchiseComponent;
    let fixture: ComponentFixture<FranchiseComponent>;
    let service: FranchiseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [FranchiseComponent]
      })
        .overrideTemplate(FranchiseComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FranchiseComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FranchiseService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Franchise(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.franchises && comp.franchises[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
