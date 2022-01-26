import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";

import {AppComponent} from "./app.component";
import {PlayFormComponent} from "./components/play-form/play-form.component";
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {IncrementFormComponent} from "./components/increment-form/increment-form.component";
import {UserTableComponent} from "./components/user-table/user-table.component";
import {StoreModule} from "@ngrx/store";
import {counterReducer} from "./store/counter.reducer";
import {MyCounterComponent} from "./components/my-counter/my-counter.component";

@NgModule({
  declarations: [
    AppComponent,
    PlayFormComponent,
    IncrementFormComponent,
    UserTableComponent,
    MyCounterComponent,
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    StoreModule.forRoot({count: counterReducer}),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}
