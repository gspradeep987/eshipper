import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IClient, Client } from '../client.model';
import { ClientService } from '../service/client.service';
import { ISisyphusJob } from 'app/entities/sisyphus-job/sisyphus-job.model';
import { SisyphusJobService } from 'app/entities/sisyphus-job/service/sisyphus-job.service';

@Component({
  selector: 'jhi-client-update',
  templateUrl: './client-update.component.html',
})
export class ClientUpdateComponent implements OnInit {
  isSaving = false;

  sisyphusJobsSharedCollection: ISisyphusJob[] = [];

  editForm = this.fb.group({
    id: [],
    iD: [],
    nAME: [],
    cONTACT: [],
    dEFAULTFOLDER: [],
    client: [],
  });

  constructor(
    protected clientService: ClientService,
    protected sisyphusJobService: SisyphusJobService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ client }) => {
      this.updateForm(client);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const client = this.createFromForm();
    if (client.id !== undefined) {
      this.subscribeToSaveResponse(this.clientService.update(client));
    } else {
      this.subscribeToSaveResponse(this.clientService.create(client));
    }
  }

  trackSisyphusJobById(index: number, item: ISisyphusJob): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClient>>): void {
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

  protected updateForm(client: IClient): void {
    this.editForm.patchValue({
      id: client.id,
      iD: client.iD,
      nAME: client.nAME,
      cONTACT: client.cONTACT,
      dEFAULTFOLDER: client.dEFAULTFOLDER,
      client: client.client,
    });

    this.sisyphusJobsSharedCollection = this.sisyphusJobService.addSisyphusJobToCollectionIfMissing(
      this.sisyphusJobsSharedCollection,
      client.client
    );
  }

  protected loadRelationshipsOptions(): void {
    this.sisyphusJobService
      .query()
      .pipe(map((res: HttpResponse<ISisyphusJob[]>) => res.body ?? []))
      .pipe(
        map((sisyphusJobs: ISisyphusJob[]) =>
          this.sisyphusJobService.addSisyphusJobToCollectionIfMissing(sisyphusJobs, this.editForm.get('client')!.value)
        )
      )
      .subscribe((sisyphusJobs: ISisyphusJob[]) => (this.sisyphusJobsSharedCollection = sisyphusJobs));
  }

  protected createFromForm(): IClient {
    return {
      ...new Client(),
      id: this.editForm.get(['id'])!.value,
      iD: this.editForm.get(['iD'])!.value,
      nAME: this.editForm.get(['nAME'])!.value,
      cONTACT: this.editForm.get(['cONTACT'])!.value,
      dEFAULTFOLDER: this.editForm.get(['dEFAULTFOLDER'])!.value,
      client: this.editForm.get(['client'])!.value,
    };
  }
}
