import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IElasticSearchWoSalesAgent, ElasticSearchWoSalesAgent } from 'app/shared/model/elastic-search-wo-sales-agent.model';
import { ElasticSearchWoSalesAgentService } from './elastic-search-wo-sales-agent.service';
import { IElasticStatus } from 'app/shared/model/elastic-status.model';
import { ElasticStatusService } from 'app/entities/elastic-status/elastic-status.service';

@Component({
  selector: 'jhi-elastic-search-wo-sales-agent-update',
  templateUrl: './elastic-search-wo-sales-agent-update.component.html',
})
export class ElasticSearchWoSalesAgentUpdateComponent implements OnInit {
  isSaving = false;
  elasticstatuses: IElasticStatus[] = [];

  editForm = this.fb.group({
    id: [],
    statusId: [],
  });

  constructor(
    protected elasticSearchWoSalesAgentService: ElasticSearchWoSalesAgentService,
    protected elasticStatusService: ElasticStatusService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ elasticSearchWoSalesAgent }) => {
      this.updateForm(elasticSearchWoSalesAgent);

      this.elasticStatusService.query().subscribe((res: HttpResponse<IElasticStatus[]>) => (this.elasticstatuses = res.body || []));
    });
  }

  updateForm(elasticSearchWoSalesAgent: IElasticSearchWoSalesAgent): void {
    this.editForm.patchValue({
      id: elasticSearchWoSalesAgent.id,
      statusId: elasticSearchWoSalesAgent.statusId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const elasticSearchWoSalesAgent = this.createFromForm();
    if (elasticSearchWoSalesAgent.id !== undefined) {
      this.subscribeToSaveResponse(this.elasticSearchWoSalesAgentService.update(elasticSearchWoSalesAgent));
    } else {
      this.subscribeToSaveResponse(this.elasticSearchWoSalesAgentService.create(elasticSearchWoSalesAgent));
    }
  }

  private createFromForm(): IElasticSearchWoSalesAgent {
    return {
      ...new ElasticSearchWoSalesAgent(),
      id: this.editForm.get(['id'])!.value,
      statusId: this.editForm.get(['statusId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IElasticSearchWoSalesAgent>>): void {
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

  trackById(index: number, item: IElasticStatus): any {
    return item.id;
  }
}
