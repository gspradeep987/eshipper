import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMarkupPrimary } from '../ecom-markup-primary.model';
import { EcomMarkupPrimaryService } from '../service/ecom-markup-primary.service';
import { EcomMarkupPrimaryDeleteDialogComponent } from '../delete/ecom-markup-primary-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-markup-primary',
  templateUrl: './ecom-markup-primary.component.html',
})
export class EcomMarkupPrimaryComponent implements OnInit {
  ecomMarkupPrimaries?: IEcomMarkupPrimary[];
  isLoading = false;

  constructor(protected ecomMarkupPrimaryService: EcomMarkupPrimaryService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomMarkupPrimaryService.query().subscribe(
      (res: HttpResponse<IEcomMarkupPrimary[]>) => {
        this.isLoading = false;
        this.ecomMarkupPrimaries = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomMarkupPrimary): number {
    return item.id!;
  }

  delete(ecomMarkupPrimary: IEcomMarkupPrimary): void {
    const modalRef = this.modalService.open(EcomMarkupPrimaryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomMarkupPrimary = ecomMarkupPrimary;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
