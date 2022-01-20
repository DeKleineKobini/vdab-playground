package world.inetum.realdolmen.playground.relay.axon;

import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import world.inetum.realdolmen.playground.api.axon.UserIncrementedEvent;
import world.inetum.realdolmen.playground.api.axon.UserPlayedEvent;

import java.util.UUID;

class UserTest {

    private static final UUID USER_UUID = UUID.randomUUID();
    private static final String EMAIL = "playground@test.com";
    private static final String COUNTRY = "be";

    private FixtureConfiguration<User> fixture;

    @BeforeEach
    void setUp() {
        fixture = new AggregateTestFixture<>(User.class);
    }

    @Test
    void shouldPlay() {
        fixture.givenNoPriorActivity()
                .when(new UserPlayCommand(USER_UUID, EMAIL, COUNTRY))
                .expectEvents(new UserPlayedEvent(USER_UUID, EMAIL, COUNTRY));
    }

    @Test
    void shouldIncrement() {
        fixture.given(new UserPlayedEvent(USER_UUID, EMAIL, COUNTRY))
                .when(new UserIncrementCommand(USER_UUID))
                .expectEvents(new UserIncrementedEvent(USER_UUID));
    }

}
