package world.inetum.realdolmen.playground.service.axon;

import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.inetum.realdolmen.playground.api.axon.UserPlayedEvent;

import java.util.UUID;

@Aggregate
public class User {

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    @AggregateIdentifier
    private UUID uuid = UUID.randomUUID();

    private String email;
    private String country;

    public User() {
    }

    @EventSourcingHandler
    public void on(UserPlayedEvent event) {
        email = event.getEmail();
        country = event.getCountry();

        LOGGER.info("on user played - {} + {}", email, country);
    }

}
