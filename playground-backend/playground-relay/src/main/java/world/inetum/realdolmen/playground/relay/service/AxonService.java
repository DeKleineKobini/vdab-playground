package world.inetum.realdolmen.playground.relay.service;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.playground.relay.axon.UserPlayCommand;

import java.util.UUID;

@Service
public class AxonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AxonService.class);

    private final CommandGateway commandGateway;

    public AxonService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public void sendPlayCommand(String email, String country) {
        LOGGER.info("Sending play command for {} in {}.", email, country);
        try {
            commandGateway.sendAndWait(new UserPlayCommand(UUID.randomUUID(), email, country));
        } catch (Exception exception) {
            LOGGER.error("Something went wrong with axon.", exception);
        }
    }

}
