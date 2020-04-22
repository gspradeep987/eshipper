import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICarrierSettings } from 'app/shared/model/carrier-settings.model';
import { CarrierSettingsService } from './carrier-settings.service';

@Component({
  templateUrl: './carrier-settings-delete-dialog.component.html'
})
export class CarrierSettingsDeleteDialogComponent {
  carrierSettings?: ICarrierSettings;

  constructor(
    protected carrierSettingsService: CarrierSettingsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.carrierSettingsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('carrierSettingsListModification');
      this.activeModal.close();
    });
  }
}
