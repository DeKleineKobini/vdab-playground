import {Interaction, Matchers} from "@pact-foundation/pact-web";
import {TestBed} from "@angular/core/testing";
import {PactUtil} from "../../testing/pact-util";
import {describePact, InitPact} from "../../testing/describe-pact";
import {UserService} from "./user.service";
import {User} from "../components/user-table/user.types";

describe("User Contracts", () => {

  let util: PactUtil;

  const initPact: InitPact = () => {
    util = new PactUtil();
    return util;
  };

  describePact(initPact, () => {

    describe("#users", () => {
      beforeAll(function (done) {
        util
          .addInteraction(new Interaction()
            .given("Default success behavior")
            .uponReceiving("user request")
            .withRequest({
              method: "GET",
              path: "/playground/api/users",
            })
            .willRespondWith({
              status: 200,
              headers: {
                "Content-Type": PactUtil.APPLICATION_JSON,
              },
              body: ResponseMatchers.USERS,
            }), done);
      });

      it("users", (done) => {
        TestBed.inject(UserService)
          .getUsers()
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

class ResponseMatchers {

  static USER = Matchers.like(<User>{
    uuid: "91d29407-82fb-48ef-8cb3-de61765a09a4",
    email: "test@vdab.be",
    country: "be",
    amount: 18,
  });

  static USERS = Matchers.eachLike(ResponseMatchers.USER);

}
