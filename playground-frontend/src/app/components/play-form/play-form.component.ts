import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PlayService} from "../../services/play.service";
import {PlayState} from "../../store/play.reducer";
import {Store} from "@ngrx/store";
import {play} from "../../store/play.actions";
import {selectError} from "../../store/play.selectors";
import {filter} from "rxjs";

@Component({
  selector: "app-play-form",
  templateUrl: "./play-form.component.html",
  styleUrls: ["./play-form.component.css"],
})
export class PlayFormComponent implements OnInit {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private playService: PlayService,
              private store: Store<PlayState>) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      email: ["", [Validators.required, Validators.pattern(/[a-zA-Z0-9]@[a-zA-Z0-9]/)]],
      country: ["be", Validators.required],
      confirmed: [false, Validators.requiredTrue],
    });

    this.store.select(selectError)
      .pipe(filter((value) => !!value)) // Only pass if there is an error, not when it went through.
      .subscribe((error) => {
        // FIXME - Error handling
        console.log("Something went wrong.", error);
      });
  }

  onSubmit(): void {
    if (!this.form.valid) return;

    this.store.dispatch(play({
      email: this.form.get("email").value,
      country: this.form.get("country").value,
    }));
    // FIXME - Response handling
  }

}
