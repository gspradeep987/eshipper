import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EshipperTestModule } from '../../../test.module';
import { User10DetailComponent } from 'app/entities/user-10/user-10-detail.component';
import { User10 } from 'app/shared/model/user-10.model';

describe('Component Tests', () => {
  describe('User10 Management Detail Component', () => {
    let comp: User10DetailComponent;
    let fixture: ComponentFixture<User10DetailComponent>;
    const route = ({ data: of({ user10: new User10(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EshipperTestModule],
        declarations: [User10DetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(User10DetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(User10DetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load user10 on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.user10).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
