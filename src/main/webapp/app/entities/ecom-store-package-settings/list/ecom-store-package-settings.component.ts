import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStorePackageSettings } from '../ecom-store-package-settings.model';
import { EcomStorePackageSettingsService } from '../service/ecom-store-package-settings.service';
import { EcomStorePackageSettingsDeleteDialogComponent } from '../delete/ecom-store-package-settings-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-store-package-settings',
  templateUrl: './ecom-store-package-settings.component.html',
})
export class EcomStorePackageSettingsComponent implements OnInit {
  ecomStorePackageSettings?: IEcomStorePackageSettings[];
  isLoading = false;

  constructor(protected ecomStorePackageSettingsService: EcomStorePackageSettingsService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomStorePackageSettingsService.query().subscribe(
      (res: HttpResponse<IEcomStorePackageSettings[]>) => {
        this.isLoading = false;
        this.ecomStorePackageSettings = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomStorePackageSettings): number {
    return item.id!;
  }

  delete(ecomStorePackageSettings: IEcomStorePackageSettings): void {
    const modalRef = this.modalService.open(EcomStorePackageSettingsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomStorePackageSettings = ecomStorePackageSettings;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
