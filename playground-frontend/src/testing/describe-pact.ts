import {TestBed} from "@angular/core/testing";
import {HttpClientModule} from "@angular/common/http";
import {PactUtil} from "./pact-util";
import {AppModule} from "../app/app.module";

export type InitPact = () => PactUtil;

/**
 * Wrapper voor integratie testen die de nodige standaard mocking opzet zodat een AppComponent kan aangemaakt worden in test context.
 *
 * @param initIntegration Callback om lokale variabelen en spies te initialiseren.
 * @param specDefinitions De callback die normaal in describe gebruikt wordt.
 */
export function describePact(initPact: InitPact, specDefinitions: () => void) {

  let pactUtil: PactUtil;

  beforeAll(function (done) {
    pactUtil = initPact();

    setTimeout(done, 2000);

    pactUtil.provider.removeInteractions();
  });

  afterAll((done) => {
    pactUtil.provider.finalize().then(done, e => done.fail(e));
  });

  /**
   * TestingModule configureren.
   */
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule, AppModule],
    });
  });

  afterEach((done) => {
    pactUtil.provider.verify()
      .then(done, (error) => {
        pactUtil.provider.removeInteractions();
        return done.fail(error);
      });
  });

  describe("Pact", specDefinitions);

}
