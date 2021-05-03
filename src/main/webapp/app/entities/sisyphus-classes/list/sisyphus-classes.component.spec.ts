import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { SisyphusClassesService } from '../service/sisyphus-classes.service';

import { SisyphusClassesComponent } from './sisyphus-classes.component';

describe('Component Tests', () => {
  describe('SisyphusClasses Management Component', () => {
    let comp: SisyphusClassesComponent;
    let fixture: ComponentFixture<SisyphusClassesComponent>;
    let service: SisyphusClassesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [SisyphusClassesComponent],
      })
        .overrideTemplate(SisyphusClassesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SisyphusClassesComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(SisyphusClassesService);

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
      expect(comp.sisyphusClasses?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
