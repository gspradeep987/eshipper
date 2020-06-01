import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IElasticStatus, ElasticStatus } from 'app/shared/model/elastic-status.model';
import { ElasticStatusService } from './elastic-status.service';

@Component({
  selector: 'jhi-elastic-status-update',
  templateUrl: './elastic-status-update.component.html',
})
export class ElasticStatusUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
  });

  constructor(protected elasticStatusService: ElasticStatusService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ elasticStatus }) => {
      this.updateForm(elasticStatus);
    });
  }

  updateForm(elasticStatus: IElasticStatus): void {
    this.editForm.patchValue({
      id: elasticStatus.id,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const elasticStatus = this.createFromForm();
    if (elasticStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.elasticStatusService.update(elasticStatus));
    } else {
      this.subscribeToSaveResponse(this.elasticStatusService.create(elasticStatus));
    }
  }

  private createFromForm(): IElasticStatus {
    return {
      ...new ElasticStatus(),
      id: this.editForm.get(['id'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IElasticStatus>>): void {
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
}
