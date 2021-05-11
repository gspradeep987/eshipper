import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { SisyphusClientComponent } from './list/sisyphus-client.component';
import { SisyphusClientDetailComponent } from './detail/sisyphus-client-detail.component';
import { SisyphusClientUpdateComponent } from './update/sisyphus-client-update.component';
import { SisyphusClientDeleteDialogComponent } from './delete/sisyphus-client-delete-dialog.component';
import { SisyphusClientRoutingModule } from './route/sisyphus-client-routing.module';

@NgModule({
  imports: [SharedModule, SisyphusClientRoutingModule],
  declarations: [
    SisyphusClientComponent,
    SisyphusClientDetailComponent,
    SisyphusClientUpdateComponent,
    SisyphusClientDeleteDialogComponent,
  ],
  entryComponents: [SisyphusClientDeleteDialogComponent],
})
export class SisyphusClientModule {}
