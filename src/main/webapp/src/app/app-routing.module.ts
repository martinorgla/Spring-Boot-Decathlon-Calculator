import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {DecathlonComponent} from "./decathlon/decathlon.component";
import {HistoryComponent} from "./history/history.component";

const routes: Routes = [
  { path: '', component: DecathlonComponent },
  { path: 'history', component: HistoryComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
