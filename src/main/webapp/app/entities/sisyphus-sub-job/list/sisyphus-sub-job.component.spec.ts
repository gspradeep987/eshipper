import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { SisyphusSubJobService } from '../service/sisyphus-sub-job.service';

import { SisyphusSubJobComponent } from './sisyphus-sub-job.component';

describe('Component Tests', () => {
  describe('SisyphusSubJob Management Component', () => {
    let comp: SisyphusSubJobComponent;
    let fixture: ComponentFixture<SisyphusSubJobComponent>;
    let service: SisyphusSubJobService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [SisyphusSubJobComponent],
      })
        .overrideTemplate(SisyphusSubJobComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SisyphusSubJobComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(SisyphusSubJobService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ id: 123 }],
            headers,
          })
        )
      );
    });

    it('Should call load all on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.sisyphusSubJobs?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
