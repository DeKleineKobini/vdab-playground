/**
 * Static test util methodes...
 */
import {Type} from "@angular/core";
import {TestBed} from "@angular/core/testing";

export class TestUtil {

  /**
   * Detecteert of functie 'method' op object 'object' reeds een Spy is.
   * Geeft de bestaande Spy terug of creërt een Spy indien er nog geen was.
   */
  static getOrCreateSpy<T>(object: T, method: keyof T): jasmine.Spy;
  /**
   * Detecteert of functie 'method' op singleton van type 'type' reeds een Spy is.
   * Geeft de bestaande Spy terug of creërt een Spy indien er nog geen was.
   */
  static getOrCreateSpy<T>(type: Type<T>, method: keyof T): jasmine.Spy;
  static getOrCreateSpy<T>(objectOrType: T | Type<T>, method: any): jasmine.Spy {
    const object: T = objectOrType instanceof Type ? TestBed.inject(objectOrType) : objectOrType;
    if (object[method]["and"] != null) {
      return object[method] as unknown as jasmine.Spy;
    }
    return spyOn(object, method);
  }

}
