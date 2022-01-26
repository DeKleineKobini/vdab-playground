import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {AppComponent} from "./app.component";
import {PlayFormComponent} from "./components/play-form/play-form.component";
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {IncrementFormComponent} from "./components/increment-form/increment-form.component";
import {UserTableComponent} from "./components/user-table/user-table.component";
import {StoreModule} from "@ngrx/store";
import {MyCounterComponent} from "./components/my-counter/my-counter.component";
import {EffectsModule} from "@ngrx/effects";
import {effects, reducers} from "./store";

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
    StoreModule.forRoot(reducers),
    EffectsModule.forRoot(effects),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
}
