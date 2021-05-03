import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { JobTypeService } from '../service/job-type.service';

import { JobTypeComponent } from './job-type.component';

describe('Component Tests', () => {
  describe('JobType Management Component', () => {
    let comp: JobTypeComponent;
    let fixture: ComponentFixture<JobTypeComponent>;
    let service: JobTypeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [JobTypeComponent],
      })
        .overrideTemplate(JobTypeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(JobTypeComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(JobTypeService);

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
      expect(comp.jobTypes?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
