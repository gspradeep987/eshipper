import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomStorePackageSettings } from 'app/shared/model/ecom-store-package-settings.model';
import { EcomStorePackageSettingsService } from './ecom-store-package-settings.service';

@Component({
  templateUrl: './ecom-store-package-settings-delete-dialog.component.html'
})
export class EcomStorePackageSettingsDeleteDialogComponent {
  ecomStorePackageSettings?: IEcomStorePackageSettings;

  constructor(
    protected ecomStorePackageSettingsService: EcomStorePackageSettingsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStorePackageSettingsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomStorePackageSettingsListModification');
      this.activeModal.close();
    });
  }
}
