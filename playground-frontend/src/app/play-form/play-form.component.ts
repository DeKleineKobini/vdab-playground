import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PlayDto} from "./play.dto";
import {PlayService} from "../play.service";

@Component({
  selector: "app-play-form",
  templateUrl: "./play-form.component.html",
  styleUrls: ["./play-form.component.css"],
})
export class PlayFormComponent implements OnInit {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private playService: PlayService) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      email: ["", [Validators.required, Validators.pattern(/[a-zA-Z0-9]@[a-zA-Z0-9]/)]],
      country: ["be", Validators.required],
      confirmed: [false, Validators.requiredTrue],
    });
  }

  onSubmit(): void {
    if (!this.form.valid) return;

    const dto: PlayDto = {
      email: this.form.get("email").value,
      country: this.form.get("country").value,
    };

    // FIXME - Error handling
    this.playService.play(dto)
      .subscribe(() => {
        // FIXME - Response handling
      });
  }

}
