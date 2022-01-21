import {ComponentFixture, fakeAsync, TestBed} from "@angular/core/testing";
import {PlayFormComponent} from "./play-form.component";
import {ComponentPage} from "../../../testing/component-page";
import {Type} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {TestUtil} from "../../../testing/test-util";
import {of} from "rxjs";
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientTestingModule} from "@angular/common/http/testing";

describe("RegisterFormComponent", () => {

  let component: PlayFormComponent;
  let fixture: ComponentFixture<PlayFormComponent>;
  let page: PlayFormComponentPage;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, ReactiveFormsModule],
      declarations: [PlayFormComponent],
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayFormComponent);
    fixture.detectChanges();

    page = new PlayFormComponentPage(fixture);

    component = page.component;
  });

  it("should create", () => {
    expect(fixture.componentInstance).toBeTruthy();
  });

  describe("Play form", () => {

    function fillForm() {
      page.setInputValue(page.emailField, "test@test.test", true);
      page.setInputValue(page.countryField, "fr", true);
      page.setCheckboxValue(page.confirmField, true, true);
    }

    beforeEach(() => {
      TestUtil.getOrCreateSpy(HttpClient, "request"); // Alle HTTP requests blokkeren
    });

    it("Bevat formulier", () => {
      expect(component).withContext("De component moet aangemaakt zijn").toBeTruthy();
      expect(component.form).withContext("De component moet een formulier bevatten").toBeTruthy();
    });

    it("Submit knop is disabled bij foutive input", () => {
      expect(page.submitButton.disabled).withContext("Submit knop is disabled").toBeTrue();
    });

    it("Play", fakeAsync(() => {
      const httpPostSpy: jasmine.Spy = TestUtil.getOrCreateSpy(HttpClient, "post").and.returnValue(of({}));

      fillForm();
      expect(component.form.valid).withContext("Form should have been valid").toBeTrue();
      expect(page.submitButton.disabled).withContext("Button should be enabled").toBeFalse();
      page.clickOn(page.submitButton);

      expect(httpPostSpy).toHaveBeenCalled();
    }));

  });


});

export class PlayFormComponentPage extends ComponentPage<PlayFormComponent> {

  get emailField(): HTMLInputElement {
    return this.query<HTMLInputElement>("#email");
  }

  get countryField(): HTMLSelectElement {
    return this.query<HTMLSelectElement>("#country");
  }

  get confirmField(): HTMLInputElement {
    return this.query<HTMLInputElement>("#confirm");
  }

  get submitButton(): HTMLButtonElement {
    return this.query<HTMLButtonElement>("button[type='submit']");
  }

  protected getRootComponentSelector(): Type<PlayFormComponent> {
    return PlayFormComponent;
  }

}
