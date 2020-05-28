import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUser10, User10 } from 'app/shared/model/user-10.model';
import { User10Service } from './user-10.service';
import { IWoSalesAgent } from 'app/shared/model/wo-sales-agent.model';
import { WoSalesAgentService } from 'app/entities/wo-sales-agent/wo-sales-agent.service';

@Component({
  selector: 'jhi-user-10-update',
  templateUrl: './user-10-update.component.html',
})
export class User10UpdateComponent implements OnInit {
  isSaving = false;
  wosalesagents: IWoSalesAgent[] = [];

  editForm = this.fb.group({
    id: [],
    woSalesAgentId: [],
  });

  constructor(
    protected user10Service: User10Service,
    protected woSalesAgentService: WoSalesAgentService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ user10 }) => {
      this.updateForm(user10);

      this.woSalesAgentService.query().subscribe((res: HttpResponse<IWoSalesAgent[]>) => (this.wosalesagents = res.body || []));
    });
  }

  updateForm(user10: IUser10): void {
    this.editForm.patchValue({
      id: user10.id,
      woSalesAgentId: user10.woSalesAgentId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const user10 = this.createFromForm();
    if (user10.id !== undefined) {
      this.subscribeToSaveResponse(this.user10Service.update(user10));
    } else {
      this.subscribeToSaveResponse(this.user10Service.create(user10));
    }
  }

  private createFromForm(): IUser10 {
    return {
      ...new User10(),
      id: this.editForm.get(['id'])!.value,
      woSalesAgentId: this.editForm.get(['woSalesAgentId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUser10>>): void {
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

  trackById(index: number, item: IWoSalesAgent): any {
    return item.id;
  }
}
