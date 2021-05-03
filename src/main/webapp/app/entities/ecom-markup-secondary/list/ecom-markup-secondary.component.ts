import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMarkupSecondary } from '../ecom-markup-secondary.model';
import { EcomMarkupSecondaryService } from '../service/ecom-markup-secondary.service';
import { EcomMarkupSecondaryDeleteDialogComponent } from '../delete/ecom-markup-secondary-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-markup-secondary',
  templateUrl: './ecom-markup-secondary.component.html',
})
export class EcomMarkupSecondaryComponent implements OnInit {
  ecomMarkupSecondaries?: IEcomMarkupSecondary[];
  isLoading = false;

  constructor(protected ecomMarkupSecondaryService: EcomMarkupSecondaryService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomMarkupSecondaryService.query().subscribe(
      (res: HttpResponse<IEcomMarkupSecondary[]>) => {
        this.isLoading = false;
        this.ecomMarkupSecondaries = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomMarkupSecondary): number {
    return item.id!;
  }

  delete(ecomMarkupSecondary: IEcomMarkupSecondary): void {
    const modalRef = this.modalService.open(EcomMarkupSecondaryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomMarkupSecondary = ecomMarkupSecondary;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
