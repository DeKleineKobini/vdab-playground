import {Interaction, PactWeb} from "@pact-foundation/pact-web";

export class PactUtil {

  static readonly APPLICATION_JSON_UTF8: string = "application/json; charset=UTF-8";

  provider: PactWeb;

  constructor() {
    this.provider = new PactWeb({
      port: 9999,
    });
  }

  addInteraction(interaction: Interaction, done: DoneFn) {
    this.provider.addInteraction(interaction).then(done, (error) => done.fail(error));
  }

}
