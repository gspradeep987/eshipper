import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EshipperSharedModule } from 'app/shared/shared.module';
import { User10Component } from './user-10.component';
import { User10DetailComponent } from './user-10-detail.component';
import { User10UpdateComponent } from './user-10-update.component';
import { User10DeleteDialogComponent } from './user-10-delete-dialog.component';
import { user10Route } from './user-10.route';

@NgModule({
  imports: [EshipperSharedModule, RouterModule.forChild(user10Route)],
  declarations: [User10Component, User10DetailComponent, User10UpdateComponent, User10DeleteDialogComponent],
  entryComponents: [User10DeleteDialogComponent]
})
export class EshipperUser10Module {}
