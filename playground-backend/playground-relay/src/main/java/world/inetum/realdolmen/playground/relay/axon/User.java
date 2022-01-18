package world.inetum.realdolmen.playground.relay.axon;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class User {

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    @AggregateIdentifier
    private UUID uuid = UUID.randomUUID();

    private String email;
    private String country;

    public User() {
    }

    @CommandHandler
    public User(UserPlayCommand command) {
        if (command.getCountry() == null)
            throw new IllegalArgumentException("country is null");

        apply(new UserPlayedEvent(command.getEmail(), command.getCountry()));
    }

    @EventSourcingHandler
    public void on(UserPlayedEvent event) {
        email = event.getEmail();
        country = event.getCountry();

        LOGGER.info("on user played - {} + {}", email, country);
    }

}
