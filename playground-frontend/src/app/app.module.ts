import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";

import {AppComponent} from "./app.component";
import {PlayFormComponent} from "./play-form/play-form.component";
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {IncrementFormComponent} from "./increment-form/increment-form.component";
import {UserTableComponent} from "./user-table/user-table.component";

@NgModule({
  declarations: [
    AppComponent,
    PlayFormComponent,
    IncrementFormComponent,
    UserTableComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}
