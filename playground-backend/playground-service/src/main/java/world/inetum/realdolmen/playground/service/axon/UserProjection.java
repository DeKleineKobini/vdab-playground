package world.inetum.realdolmen.playground.service.axon;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import world.inetum.realdolmen.playground.api.axon.UserPlayedEvent;
import world.inetum.realdolmen.playground.service.persistance.entities.UserEntity;
import world.inetum.realdolmen.playground.service.persistance.repositories.UserRepository;

@Component
public class UserProjection {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserProjection.class);

    private final UserRepository userRepository;

    public UserProjection(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandler
    public void on(UserPlayedEvent event) {
        UserEntity user = new UserEntity(event.getUuid(), event.getEmail(), event.getCountry());

        userRepository.save(user);
    }

    @ResetHandler
    public void reset() {
        userRepository.deleteAll();

        LOGGER.info("Cleared out UserEntity table.");
    }

}
