import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreColorTheme } from '../ecom-store-color-theme.model';
import { EcomStoreColorThemeService } from '../service/ecom-store-color-theme.service';
import { EcomStoreColorThemeDeleteDialogComponent } from '../delete/ecom-store-color-theme-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store-color-theme',
  templateUrl: './ecom-store-color-theme.component.html',
})
export class EcomStoreColorThemeComponent implements OnInit {
  ecomStoreColorThemes?: IEcomStoreColorTheme[];
  isLoading = false;

  constructor(protected ecomStoreColorThemeService: EcomStoreColorThemeService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomStoreColorThemeService.query().subscribe(
      (res: HttpResponse<IEcomStoreColorTheme[]>) => {
        this.isLoading = false;
        this.ecomStoreColorThemes = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomStoreColorTheme): number {
    return item.id!;
  }

  delete(ecomStoreColorTheme: IEcomStoreColorTheme): void {
    const modalRef = this.modalService.open(EcomStoreColorThemeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStoreColorTheme = ecomStoreColorTheme;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
