import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { MatButtonModule, MatFormFieldModule, MatInputModule } from '@angular/material';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DecathlonComponent } from './decathlon/decathlon.component';
import { HttpClientModule } from "@angular/common/http";
import {MatGridListModule} from "@angular/material/grid-list";
import {FormsModule} from "@angular/forms";
import {MatTableModule} from "@angular/material/table";
import {MatListModule} from "@angular/material/list";

@NgModule({
  declarations: [
    AppComponent,
    DecathlonComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatFormFieldModule, MatButtonModule, MatInputModule,
        HttpClientModule, MatGridListModule, FormsModule, MatTableModule, MatListModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
