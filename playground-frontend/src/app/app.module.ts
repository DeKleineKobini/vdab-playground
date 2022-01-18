import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";

import {AppComponent} from "./app.component";
import {PlayFormComponent} from "./play-form/play-form.component";
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    PlayFormComponent,
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
