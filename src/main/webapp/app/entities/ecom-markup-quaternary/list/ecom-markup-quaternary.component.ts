import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMarkupQuaternary } from '../ecom-markup-quaternary.model';
import { EcomMarkupQuaternaryService } from '../service/ecom-markup-quaternary.service';
import { EcomMarkupQuaternaryDeleteDialogComponent } from '../delete/ecom-markup-quaternary-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-markup-quaternary',
  templateUrl: './ecom-markup-quaternary.component.html',
})
export class EcomMarkupQuaternaryComponent implements OnInit {
  ecomMarkupQuaternaries?: IEcomMarkupQuaternary[];
  isLoading = false;

  constructor(protected ecomMarkupQuaternaryService: EcomMarkupQuaternaryService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomMarkupQuaternaryService.query().subscribe(
      (res: HttpResponse<IEcomMarkupQuaternary[]>) => {
        this.isLoading = false;
        this.ecomMarkupQuaternaries = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomMarkupQuaternary): number {
    return item.id!;
  }

  delete(ecomMarkupQuaternary: IEcomMarkupQuaternary): void {
    const modalRef = this.modalService.open(EcomMarkupQuaternaryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomMarkupQuaternary = ecomMarkupQuaternary;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
