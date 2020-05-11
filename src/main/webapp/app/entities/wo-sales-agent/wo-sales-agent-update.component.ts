import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IWoSalesAgent, WoSalesAgent } from 'app/shared/model/wo-sales-agent.model';
import { WoSalesAgentService } from './wo-sales-agent.service';
import { IWoSalesAgentDetails } from 'app/shared/model/wo-sales-agent-details.model';
import { WoSalesAgentDetailsService } from 'app/entities/wo-sales-agent-details/wo-sales-agent-details.service';
import { IWoSalesCommissionDetails } from 'app/shared/model/wo-sales-commission-details.model';
import { WoSalesCommissionDetailsService } from 'app/entities/wo-sales-commission-details/wo-sales-commission-details.service';
import { IWoSalesOperationalDetails } from 'app/shared/model/wo-sales-operational-details.model';
import { WoSalesOperationalDetailsService } from 'app/entities/wo-sales-operational-details/wo-sales-operational-details.service';
import { IWoSalesCommissionTransfer } from 'app/shared/model/wo-sales-commission-transfer.model';
import { WoSalesCommissionTransferService } from 'app/entities/wo-sales-commission-transfer/wo-sales-commission-transfer.service';
import { ISalesAgentType } from 'app/shared/model/sales-agent-type.model';
import { SalesAgentTypeService } from 'app/entities/sales-agent-type/sales-agent-type.service';

type SelectableEntity =
  | IWoSalesAgentDetails
  | IWoSalesCommissionDetails
  | IWoSalesOperationalDetails
  | IWoSalesCommissionTransfer
  | ISalesAgentType;

@Component({
  selector: 'jhi-wo-sales-agent-update',
  templateUrl: './wo-sales-agent-update.component.html'
})
export class WoSalesAgentUpdateComponent implements OnInit {
  isSaving = false;
  wosalesagentdetails: IWoSalesAgentDetails[] = [];
  wosalescommissiondetails: IWoSalesCommissionDetails[] = [];
  wosalesoperationaldetails: IWoSalesOperationalDetails[] = [];
  wosalescommissiontransfers: IWoSalesCommissionTransfer[] = [];
  salesagenttypes: ISalesAgentType[] = [];

  editForm = this.fb.group({
    id: [],
    isActive: [],
    woSalesAgentDetailsId: [],
    woSalesCommissionDetailsId: [],
    woSalesOperationalDetailsId: [],
    woSalesCommissionTransferId: [],
    salesAgentTypeId: []
  });

  constructor(
    protected woSalesAgentService: WoSalesAgentService,
    protected woSalesAgentDetailsService: WoSalesAgentDetailsService,
    protected woSalesCommissionDetailsService: WoSalesCommissionDetailsService,
    protected woSalesOperationalDetailsService: WoSalesOperationalDetailsService,
    protected woSalesCommissionTransferService: WoSalesCommissionTransferService,
    protected salesAgentTypeService: SalesAgentTypeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ woSalesAgent }) => {
      this.updateForm(woSalesAgent);

      this.woSalesAgentDetailsService
        .query({ filter: 'wosalesagent-is-null' })
        .pipe(
          map((res: HttpResponse<IWoSalesAgentDetails[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IWoSalesAgentDetails[]) => {
          if (!woSalesAgent.woSalesAgentDetailsId) {
            this.wosalesagentdetails = resBody;
          } else {
            this.woSalesAgentDetailsService
              .find(woSalesAgent.woSalesAgentDetailsId)
              .pipe(
                map((subRes: HttpResponse<IWoSalesAgentDetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IWoSalesAgentDetails[]) => (this.wosalesagentdetails = concatRes));
          }
        });

      this.woSalesCommissionDetailsService
        .query({ filter: 'wosalesagent-is-null' })
        .pipe(
          map((res: HttpResponse<IWoSalesCommissionDetails[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IWoSalesCommissionDetails[]) => {
          if (!woSalesAgent.woSalesCommissionDetailsId) {
            this.wosalescommissiondetails = resBody;
          } else {
            this.woSalesCommissionDetailsService
              .find(woSalesAgent.woSalesCommissionDetailsId)
              .pipe(
                map((subRes: HttpResponse<IWoSalesCommissionDetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IWoSalesCommissionDetails[]) => (this.wosalescommissiondetails = concatRes));
          }
        });

      this.woSalesOperationalDetailsService
        .query({ filter: 'wosalesagent-is-null' })
        .pipe(
          map((res: HttpResponse<IWoSalesOperationalDetails[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IWoSalesOperationalDetails[]) => {
          if (!woSalesAgent.woSalesOperationalDetailsId) {
            this.wosalesoperationaldetails = resBody;
          } else {
            this.woSalesOperationalDetailsService
              .find(woSalesAgent.woSalesOperationalDetailsId)
              .pipe(
                map((subRes: HttpResponse<IWoSalesOperationalDetails>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IWoSalesOperationalDetails[]) => (this.wosalesoperationaldetails = concatRes));
          }
        });

      this.woSalesCommissionTransferService
        .query({ filter: 'wosalesagent-is-null' })
        .pipe(
          map((res: HttpResponse<IWoSalesCommissionTransfer[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IWoSalesCommissionTransfer[]) => {
          if (!woSalesAgent.woSalesCommissionTransferId) {
            this.wosalescommissiontransfers = resBody;
          } else {
            this.woSalesCommissionTransferService
              .find(woSalesAgent.woSalesCommissionTransferId)
              .pipe(
                map((subRes: HttpResponse<IWoSalesCommissionTransfer>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IWoSalesCommissionTransfer[]) => (this.wosalescommissiontransfers = concatRes));
          }
        });

      this.salesAgentTypeService.query().subscribe((res: HttpResponse<ISalesAgentType[]>) => (this.salesagenttypes = res.body || []));
    });
  }

  updateForm(woSalesAgent: IWoSalesAgent): void {
    this.editForm.patchValue({
      id: woSalesAgent.id,
      isActive: woSalesAgent.isActive,
      woSalesAgentDetailsId: woSalesAgent.woSalesAgentDetailsId,
      woSalesCommissionDetailsId: woSalesAgent.woSalesCommissionDetailsId,
      woSalesOperationalDetailsId: woSalesAgent.woSalesOperationalDetailsId,
      woSalesCommissionTransferId: woSalesAgent.woSalesCommissionTransferId,
      salesAgentTypeId: woSalesAgent.salesAgentTypeId
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const woSalesAgent = this.createFromForm();
    if (woSalesAgent.id !== undefined) {
      this.subscribeToSaveResponse(this.woSalesAgentService.update(woSalesAgent));
    } else {
      this.subscribeToSaveResponse(this.woSalesAgentService.create(woSalesAgent));
    }
  }

  private createFromForm(): IWoSalesAgent {
    return {
      ...new WoSalesAgent(),
      id: this.editForm.get(['id'])!.value,
      isActive: this.editForm.get(['isActive'])!.value,
      woSalesAgentDetailsId: this.editForm.get(['woSalesAgentDetailsId'])!.value,
      woSalesCommissionDetailsId: this.editForm.get(['woSalesCommissionDetailsId'])!.value,
      woSalesOperationalDetailsId: this.editForm.get(['woSalesOperationalDetailsId'])!.value,
      woSalesCommissionTransferId: this.editForm.get(['woSalesCommissionTransferId'])!.value,
      salesAgentTypeId: this.editForm.get(['salesAgentTypeId'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWoSalesAgent>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
