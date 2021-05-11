import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMarkupTertiary } from '../ecom-markup-tertiary.model';
import { EcomMarkupTertiaryService } from '../service/ecom-markup-tertiary.service';
import { EcomMarkupTertiaryDeleteDialogComponent } from '../delete/ecom-markup-tertiary-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-markup-tertiary',
  templateUrl: './ecom-markup-tertiary.component.html',
})
export class EcomMarkupTertiaryComponent implements OnInit {
  ecomMarkupTertiaries?: IEcomMarkupTertiary[];
  isLoading = false;

  constructor(protected ecomMarkupTertiaryService: EcomMarkupTertiaryService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomMarkupTertiaryService.query().subscribe(
      (res: HttpResponse<IEcomMarkupTertiary[]>) => {
        this.isLoading = false;
        this.ecomMarkupTertiaries = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomMarkupTertiary): number {
    return item.id!;
  }

  delete(ecomMarkupTertiary: IEcomMarkupTertiary): void {
    const modalRef = this.modalService.open(EcomMarkupTertiaryDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomMarkupTertiary = ecomMarkupTertiary;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
