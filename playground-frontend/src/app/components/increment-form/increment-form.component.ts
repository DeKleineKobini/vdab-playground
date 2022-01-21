import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PlayService} from "../../services/play.service";
import {IncrementDto} from "./increment.dto";

@Component({
  selector: "app-increment-form",
  templateUrl: "./increment-form.component.html",
  styleUrls: ["./increment-form.component.css"],
})
export class IncrementFormComponent implements OnInit {

  form: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private playService: PlayService) {
  }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      uuid: ["", Validators.required],
    });
  }

  onSubmit(): void {
    if (!this.form.valid) return;

    const dto: IncrementDto = {
      uuid: this.form.get("uuid").value,
    };

    // FIXME - Error handling
    this.playService.increment(dto)
      .subscribe(() => {
        // FIXME - Response handling
        this.form.reset();
      });
  }

}
