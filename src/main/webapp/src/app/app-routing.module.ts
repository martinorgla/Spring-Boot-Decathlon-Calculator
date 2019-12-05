import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DecathlonComponent} from "./decathlon/decathlon.component";

const routes: Routes = [
  { path: '', component: DecathlonComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
