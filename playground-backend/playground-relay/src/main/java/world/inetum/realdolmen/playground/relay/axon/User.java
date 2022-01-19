package world.inetum.realdolmen.playground.relay.axon;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.inetum.realdolmen.playground.api.axon.UserIncrementedEvent;
import world.inetum.realdolmen.playground.api.axon.UserPlayedEvent;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class User {

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    @AggregateIdentifier
    private UUID uuid;
    private String email;
    private String country;

    private int amount = 0;

    public User() {
    }

    @CommandHandler
    public User(UserPlayCommand command) {
        if (command.getCountry() == null)
            throw new IllegalArgumentException("country is null");

        apply(new UserPlayedEvent(command.getUuid(), command.getEmail(), command.getCountry()));
    }

    @CommandHandler
    public void handle(UserIncrementCommand command) {
        apply(new UserIncrementedEvent(command.getUuid()));
    }

    @EventSourcingHandler
    public void on(UserPlayedEvent event) {
        uuid = event.getUuid();
        email = event.getEmail();
        country = event.getCountry();

        LOGGER.info("on user played - {} ({}) + {}", email, uuid, country);
    }

    @EventSourcingHandler
    public void on(UserIncrementedEvent event) {
        amount++;

        LOGGER.info("on user increment - {} to {}", uuid, amount);
    }


}
