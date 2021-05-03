import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISisyphusClasses } from '../sisyphus-classes.model';
import { SisyphusClassesService } from '../service/sisyphus-classes.service';
import { SisyphusClassesDeleteDialogComponent } from '../delete/sisyphus-classes-delete-dialog.component';

@Component({
  selector: 'jhi-sisyphus-classes',
  templateUrl: './sisyphus-classes.component.html',
})
export class SisyphusClassesComponent implements OnInit {
  sisyphusClasses?: ISisyphusClasses[];
  isLoading = false;

  constructor(protected sisyphusClassesService: SisyphusClassesService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.sisyphusClassesService.query().subscribe(
      (res: HttpResponse<ISisyphusClasses[]>) => {
        this.isLoading = false;
        this.sisyphusClasses = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: ISisyphusClasses): number {
    return item.id!;
  }

  delete(sisyphusClasses: ISisyphusClasses): void {
    const modalRef = this.modalService.open(SisyphusClassesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.sisyphusClasses = sisyphusClasses;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
