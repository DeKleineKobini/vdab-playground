import {ComponentFixture, fakeAsync, TestBed} from "@angular/core/testing";
import {IncrementFormComponent} from "./increment-form.component";
import {ComponentPage} from "../../testing/component-page";
import {Type} from "@angular/core";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {ReactiveFormsModule} from "@angular/forms";
import {TestUtil} from "../../testing/test-util";
import {HttpClient} from "@angular/common/http";
import {of} from "rxjs";

describe("IncrementFormComponent", () => {

  let component: IncrementFormComponent;
  let fixture: ComponentFixture<IncrementFormComponent>;
  let page: IncrementFormComponentPage;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, ReactiveFormsModule],
      declarations: [IncrementFormComponent],
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IncrementFormComponent);
    fixture.detectChanges();

    page = new IncrementFormComponentPage(fixture);

    component = page.component;
  });

  it("should create", () => {
    expect(component).toBeTruthy();
  });

  describe("Increment form", () => {

    function fillForm() {
      page.setInputValue(page.uuidField, "some-uuid", true);
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

export class IncrementFormComponentPage extends ComponentPage<IncrementFormComponent> {

  get uuidField(): HTMLInputElement {
    return this.query<HTMLInputElement>("#uuid");
  }

  get submitButton(): HTMLButtonElement {
    return this.query<HTMLButtonElement>("button[type='submit']");
  }

  protected getRootComponentSelector(): Type<IncrementFormComponent> {
    return IncrementFormComponent;
  }

}
