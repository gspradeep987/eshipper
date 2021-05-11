import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomStoreColorTheme } from '../ecom-store-color-theme.model';
import { EcomStoreColorThemeService } from '../service/ecom-store-color-theme.service';

@Component({
  templateUrl: './ecom-store-color-theme-delete-dialog.component.html',
})
export class EcomStoreColorThemeDeleteDialogComponent {
  ecomStoreColorTheme?: IEcomStoreColorTheme;

  constructor(protected ecomStoreColorThemeService: EcomStoreColorThemeService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStoreColorThemeService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
