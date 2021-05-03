import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { SisyphusClassesComponent } from './list/sisyphus-classes.component';
import { SisyphusClassesDetailComponent } from './detail/sisyphus-classes-detail.component';
import { SisyphusClassesUpdateComponent } from './update/sisyphus-classes-update.component';
import { SisyphusClassesDeleteDialogComponent } from './delete/sisyphus-classes-delete-dialog.component';
import { SisyphusClassesRoutingModule } from './route/sisyphus-classes-routing.module';

@NgModule({
  imports: [SharedModule, SisyphusClassesRoutingModule],
  declarations: [
    SisyphusClassesComponent,
    SisyphusClassesDetailComponent,
    SisyphusClassesUpdateComponent,
    SisyphusClassesDeleteDialogComponent,
  ],
  entryComponents: [SisyphusClassesDeleteDialogComponent],
})
export class SisyphusClassesModule {}
