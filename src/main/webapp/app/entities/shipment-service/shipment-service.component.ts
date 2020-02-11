import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IShipmentService } from 'app/shared/model/shipment-service.model';
import { ShipmentServiceService } from './shipment-service.service';
import { ShipmentServiceDeleteDialogComponent } from './shipment-service-delete-dialog.component';

@Component({
  selector: 'jhi-shipment-service',
  templateUrl: './shipment-service.component.html'
})
export class ShipmentServiceComponent implements OnInit, OnDestroy {
  shipmentServices?: IShipmentService[];
  eventSubscriber?: Subscription;

  constructor(
    protected shipmentServiceService: ShipmentServiceService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.shipmentServiceService.query().subscribe((res: HttpResponse<IShipmentService[]>) => (this.shipmentServices = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInShipmentServices();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IShipmentService): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInShipmentServices(): void {
    this.eventSubscriber = this.eventManager.subscribe('shipmentServiceListModification', () => this.loadAll());
  }

  delete(shipmentService: IShipmentService): void {
    const modalRef = this.modalService.open(ShipmentServiceDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.shipmentService = shipmentService;
  }
}
