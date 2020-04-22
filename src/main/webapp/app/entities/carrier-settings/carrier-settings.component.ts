import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICarrierSettings } from 'app/shared/model/carrier-settings.model';
import { CarrierSettingsService } from './carrier-settings.service';
import { CarrierSettingsDeleteDialogComponent } from './carrier-settings-delete-dialog.component';

@Component({
  selector: 'jhi-carrier-settings',
  templateUrl: './carrier-settings.component.html'
})
export class CarrierSettingsComponent implements OnInit, OnDestroy {
  carrierSettings?: ICarrierSettings[];
  eventSubscriber?: Subscription;

  constructor(
    protected carrierSettingsService: CarrierSettingsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.carrierSettingsService.query().subscribe((res: HttpResponse<ICarrierSettings[]>) => (this.carrierSettings = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCarrierSettings();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICarrierSettings): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCarrierSettings(): void {
    this.eventSubscriber = this.eventManager.subscribe('carrierSettingsListModification', () => this.loadAll());
  }

  delete(carrierSettings: ICarrierSettings): void {
    const modalRef = this.modalService.open(CarrierSettingsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.carrierSettings = carrierSettings;
  }
}
