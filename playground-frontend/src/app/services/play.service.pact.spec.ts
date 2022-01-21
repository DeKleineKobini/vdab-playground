import {Interaction} from "@pact-foundation/pact-web";
import {TestBed} from "@angular/core/testing";
import {PlayService} from "./play.service";
import {PactUtil} from "../../testing/pact-util";
import {describePact, InitPact} from "../../testing/describe-pact";
import {PlayDto} from "../components/play-form/play.dto";
import {IncrementDto} from "../components/increment-form/increment.dto";

describe("Play Contracts", () => {

  let util: PactUtil;

  const initPact: InitPact = () => {
    util = new PactUtil();
    return util;
  };

  describePact(initPact, () => {

    describe("#play", () => {
      beforeAll(function (done) {
        util
          .addInteraction(new Interaction()
            .given("Default success behavior")
            .uponReceiving("play request")
            .withRequest({
              method: "POST",
              path: "/playground/api/play",
              body: MockRequests.PLAY_REQUEST_DTO,
            })
            .willRespondWith({
              status: 200,
              headers: {
                "Content-Type": PactUtil.APPLICATION_JSON_UTF8,
              },
            }), done);
      });

      it("play", (done) => {
        TestBed.inject(PlayService)
          .play(MockRequests.PLAY_REQUEST_DTO)
          .subscribe({
            next: (response) => {
              expect(response).withContext("response should be defined").toBeDefined();
              done();
            },
            error: (error) => done.fail(error),
          });
      });

    });

    describe("#increment", () => {
      beforeAll(function (done) {
        util
          .addInteraction(new Interaction()
            .given("Default success behavior")
            .uponReceiving("increment request")
            .withRequest({
              method: "POST",
              path: "/playground/api/play/increment",
              body: MockRequests.INCREMENT_REQUEST_DTO,
            })
            .willRespondWith({
              status: 200,
              headers: {
                "Content-Type": PactUtil.APPLICATION_JSON_UTF8,
              },
            }), done);
      });

      it("increment", (done) => {
        TestBed.inject(PlayService)
          .increment(MockRequests.INCREMENT_REQUEST_DTO)
          .subscribe({
            next: (response) => {
              expect(response).withContext("response should be defined").toBeDefined();
              done();
            },
            error: (error) => done.fail(error),
          });
      });

    });

  });

});

class MockRequests {

  static PLAY_REQUEST_DTO: PlayDto = {
    email: "test@vdab.be",
    country: "be",
  };

  static INCREMENT_REQUEST_DTO: IncrementDto = {
    "uuid": "91d29407-82fb-48ef-8cb3-de61765a09a4",
  };

}
