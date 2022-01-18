import {ComponentFixture, tick} from "@angular/core/testing";
import {DebugElement, Predicate, Type} from "@angular/core";
import {By} from "@angular/platform-browser";

export abstract class ComponentPage<T> {

  public fixture: ComponentFixture<any>;

  public rootSelectors: Predicate<DebugElement>[];

  /**
   * Het {@link DebugElement} van de root component van deze page.
   * Alle queries op deze page worden uitgevoerd op dit {@link DebugElement}.
   */
  private _root: DebugElement;

  /**
   * Het {@link Type} van de root component voor deze page.
   * Query's op deze page worden dan beperkt om enkel binnen het eerste voorkomende component van dit {@link Type} te zoeken.
   */
  private readonly _rootComponentSelector: Type<any>;

  constructor(fixture: ComponentFixture<any>){
    this.fixture = fixture;
    this._rootComponentSelector = this.getRootComponentSelector();
    this.rootSelectors = [By.directive(this._rootComponentSelector)];
  }

  /**
   * Geeft het default Type van de root component voor deze page.
   */
  protected abstract getRootComponentSelector(): Type<T>;

  /**
   * Initialiseert het {@link DebugElement} van de root component.
   * Indien de rootComponentSelector NULL is wordt teruggevallen op het fixture.debugElement (het hiërarchisch hoogste {@link DebugElement})
   */
  private initRootComponent(): DebugElement {
    const componentType: Type<any> = this._rootComponentSelector;
    if (componentType == null
      || this.fixture.componentInstance.constructor.name === componentType.name) {
      return this.fixture.debugElement;
    }

    return ComponentPage.getDebugElementByType(this.fixture.debugElement, componentType);
  }

  /**
   * De huidige root component.
   */
  get component(): T {
    return this.root.componentInstance;
  }

  /**
   * Het {@link DebugElement} van de huidige root component.
   * Indien de vorige instance NULL is of niet langer gerenderd ({@link Node.isConnected})
   * dan wordt eerst het {@link DebugElement} herladen met {@link ComponentPage.initRootComponent()}.
   */
  get root(): DebugElement {
    if (this._root == null || !this._root.nativeElement.isConnected) {
      this._root = this.initRootComponent();
    }
    return this._root;
  }

  static query<T extends HTMLElement>(nativeElement: HTMLElement, selector: string): T {
    return nativeElement.querySelector<T>(selector);
  }

  query<T extends HTMLElement>(selector: string): T {
    if (this.root == null) { // root component bestaat niet in de page
      return null;
    }
    return ComponentPage.query(this.root.nativeElement, selector);
  }

  static getDebugElementByType(root: DebugElement, type: Type<any>): DebugElement {
    return root.query(By.directive(type));
  }

  clickOn(element: HTMLElement): void {
    element.click();
    this.fixture.detectChanges();
  }

  /**
   * Gaat een waarde invullen in een HTML input element. Deze dient gebruikt te worden voor gewone tekstvelden, textareas, radiobuttons en dropdownlijsten.
   *
   * @param htmlElement
   * @param value
   * @param blur
   */
  setInputValue(htmlElement: HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement, value: string, blur: boolean = false) {
    if (value && typeof value === "string") {
      value = value.replace(/\s/g, ' ');
    }
    htmlElement.value = value;
    if ((htmlElement instanceof HTMLInputElement && ['text', 'email', 'tel'].includes(htmlElement.type)) || htmlElement instanceof HTMLTextAreaElement) {
      htmlElement.dispatchEvent(new Event('input'));
    } else { // HtmlSelectElement && HTMLInputElement[type=radio]
      htmlElement.dispatchEvent(new Event('change'));
    }
    if (blur) {
      htmlElement.dispatchEvent(new Event('blur'));
    }
    this.fixture.detectChanges();
    tick();
  }

  /**
   * Een checkbox al dan niet aanvinken.
   *
   * @param {HTMLInputElement} element
   * @param {boolean} blur: Dispatch blur event
   * @param {boolean} checked: De checked waarde die de checkbox moet krijgen
   */
  setCheckboxValue(element: HTMLInputElement, checked: boolean, blur: boolean = false) {
    element.checked = checked;
    element.dispatchEvent(new Event('change'));
    if (blur) {
      element.dispatchEvent(new Event('blur'));
    }
    this.fixture.detectChanges();
  }

}
