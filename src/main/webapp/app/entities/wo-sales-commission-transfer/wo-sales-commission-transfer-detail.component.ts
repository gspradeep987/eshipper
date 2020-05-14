import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWoSalesCommissionTransfer } from 'app/shared/model/wo-sales-commission-transfer.model';

@Component({
  selector: 'jhi-wo-sales-commission-transfer-detail',
  templateUrl: './wo-sales-commission-transfer-detail.component.html'
})
export class WoSalesCommissionTransferDetailComponent implements OnInit {
  woSalesCommissionTransfer: IWoSalesCommissionTransfer | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesCommissionTransfer }) => (this.woSalesCommissionTransfer = woSalesCommissionTransfer));
  }

  previousState(): void {
    window.history.back();
  }
}
