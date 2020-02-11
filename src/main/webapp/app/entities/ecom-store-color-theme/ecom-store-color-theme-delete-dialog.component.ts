import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomStoreColorTheme } from 'app/shared/model/ecom-store-color-theme.model';
import { EcomStoreColorThemeService } from './ecom-store-color-theme.service';

@Component({
  templateUrl: './ecom-store-color-theme-delete-dialog.component.html'
})
export class EcomStoreColorThemeDeleteDialogComponent {
  ecomStoreColorTheme?: IEcomStoreColorTheme;

  constructor(
    protected ecomStoreColorThemeService: EcomStoreColorThemeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStoreColorThemeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomStoreColorThemeListModification');
      this.activeModal.close();
    });
  }
}
