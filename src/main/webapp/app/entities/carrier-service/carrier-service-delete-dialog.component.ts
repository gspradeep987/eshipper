import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICarrierService } from 'app/shared/model/carrier-service.model';
import { CarrierServiceService } from './carrier-service.service';

@Component({
  templateUrl: './carrier-service-delete-dialog.component.html',
})
export class CarrierServiceDeleteDialogComponent {
  carrierService?: ICarrierService;

  constructor(
    protected carrierServiceService: CarrierServiceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.carrierServiceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('carrierServiceListModification');
      this.activeModal.close();
    });
  }
}
