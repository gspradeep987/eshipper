import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IEcomMailTemplate } from '../ecom-mail-template.model';
import { EcomMailTemplateService } from '../service/ecom-mail-template.service';
import { EcomMailTemplateDeleteDialogComponent } from '../delete/ecom-mail-template-delete-dialog.component';

@Component({
  selector: 'jhi-ecom-mail-template',
  templateUrl: './ecom-mail-template.component.html',
})
export class EcomMailTemplateComponent implements OnInit {
  ecomMailTemplates?: IEcomMailTemplate[];
  isLoading = false;

  constructor(protected ecomMailTemplateService: EcomMailTemplateService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.ecomMailTemplateService.query().subscribe(
      (res: HttpResponse<IEcomMailTemplate[]>) => {
        this.isLoading = false;
        this.ecomMailTemplates = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IEcomMailTemplate): number {
    return item.id!;
  }

  delete(ecomMailTemplate: IEcomMailTemplate): void {
    const modalRef = this.modalService.open(EcomMailTemplateDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.ecomMailTemplate = ecomMailTemplate;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
