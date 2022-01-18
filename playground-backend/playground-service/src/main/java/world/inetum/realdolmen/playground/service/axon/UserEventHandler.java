package world.inetum.realdolmen.playground.service.axon;

import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import world.inetum.realdolmen.playground.api.axon.UserPlayedEvent;

import java.util.UUID;

@Component
public class UserEventHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserEventHandler.class);

    @EventHandler
    public void on(UserPlayedEvent event) {
        UUID uuid = event.getUuid();
        String email = event.getEmail();
        String country = event.getCountry();

        LOGGER.info("on user played - {} ({}) + {}", email, uuid, country);
    }

}
