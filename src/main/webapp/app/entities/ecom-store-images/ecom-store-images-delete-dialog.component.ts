import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEcomStoreImages } from 'app/shared/model/ecom-store-images.model';
import { EcomStoreImagesService } from './ecom-store-images.service';

@Component({
  templateUrl: './ecom-store-images-delete-dialog.component.html',
})
export class EcomStoreImagesDeleteDialogComponent {
  ecomStoreImages?: IEcomStoreImages;

  constructor(
    protected ecomStoreImagesService: EcomStoreImagesService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ecomStoreImagesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ecomStoreImagesListModification');
      this.activeModal.close();
    });
  }
}
