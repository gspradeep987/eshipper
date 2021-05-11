import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { ISisyphusClient, SisyphusClient } from '../sisyphus-client.model';
import { SisyphusClientService } from '../service/sisyphus-client.service';
import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';
import { SisyphusJobService } from 'app/entities/sisyphus-job/service/sisyphus-job.service';

@Component({
  selector: 'jhi-sisyphus-client-update',
  templateUrl: './sisyphus-client-update.component.html',
})
export class SisyphusClientUpdateComponent implements OnInit {
  isSaving = false;

  sisyphusJobsSharedCollection: ISisyphusJob[] = [];

  editForm = this.fb.group({
    id: [],
    name: [],
    contact: [],
    defaultFolder: [],
    sisyphusClient: [],
  });

  constructor(
    protected sisyphusClientService: SisyphusClientService,
    protected sisyphusJobService: SisyphusJobService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ sisyphusClient }) => {
      this.updateForm(sisyphusClient);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const sisyphusClient = this.createFromForm();
    if (sisyphusClient.id !== undefined) {
      this.subscribeToSaveResponse(this.sisyphusClientService.update(sisyphusClient));
    } else {
      this.subscribeToSaveResponse(this.sisyphusClientService.create(sisyphusClient));
    }
  }

  trackSisyphusJobById(index: number, item: ISisyphusJob): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISisyphusClient>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(sisyphusClient: ISisyphusClient): void {
    this.editForm.patchValue({
      id: sisyphusClient.id,
      name: sisyphusClient.name,
      contact: sisyphusClient.contact,
      defaultFolder: sisyphusClient.defaultFolder,
      sisyphusClient: sisyphusClient.sisyphusClient,
    });

    this.sisyphusJobsSharedCollection = this.sisyphusJobService.addSisyphusJobToCollectionIfMissing(
      this.sisyphusJobsSharedCollection,
      sisyphusClient.sisyphusClient
    );
  }

  protected loadRelationshipsOptions(): void {
    this.sisyphusJobService
      .query()
      .pipe(map((res: HttpResponse<ISisyphusJob[]>) => res.body ?? []))
      .pipe(
        map((sisyphusJobs: ISisyphusJob[]) =>
          this.sisyphusJobService.addSisyphusJobToCollectionIfMissing(sisyphusJobs, this.editForm.get('sisyphusClient')!.value)
        )
      )
      .subscribe((sisyphusJobs: ISisyphusJob[]) => (this.sisyphusJobsSharedCollection = sisyphusJobs));
  }

  protected createFromForm(): ISisyphusClient {
    return {
      ...new SisyphusClient(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      contact: this.editForm.get(['contact'])!.value,
      defaultFolder: this.editForm.get(['defaultFolder'])!.value,
      sisyphusClient: this.editForm.get(['sisyphusClient'])!.value,
    };
  }
}
