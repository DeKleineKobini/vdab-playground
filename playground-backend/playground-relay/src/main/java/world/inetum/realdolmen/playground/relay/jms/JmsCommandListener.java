package world.inetum.realdolmen.playground.relay.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import world.inetum.realdolmen.playground.api.IncrementCommand;
import world.inetum.realdolmen.playground.api.PlayCommand;
import world.inetum.realdolmen.playground.api.jms.JmsUtil;
import world.inetum.realdolmen.playground.api.jms.PlaygroundCommand;
import world.inetum.realdolmen.playground.relay.service.AxonService;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class JmsCommandListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(JmsCommandListener.class);

    private final ObjectMapper objectMapper;
    private final AxonService axonService;

    public JmsCommandListener(ObjectMapper objectMapper, AxonService axonService) {
        this.objectMapper = objectMapper;
        this.axonService = axonService;
    }

    @JmsListener(destination = "${jms.queue:playground.queue}")
    public void onMessage(final Message message) {
        try {
            final String commandName = JmsUtil.getCommandName(message);
            PlaygroundCommand playgroundCommand = PlaygroundCommand.ofName(commandName);
            if (playgroundCommand == null) {
                LOGGER.warn("Couldn't process command due to incorrect command name: {}.", commandName);
                return;
            }

            final String body = message.getBody(String.class);
            if (PlaygroundCommand.PLAY_COMMAND.equals(playgroundCommand)) {
                PlayCommand command = objectMapper.readValue(body, PlayCommand.class);

                axonService.sendPlayCommand(command.getEmail(), command.getCountry());
            } else if (PlaygroundCommand.INCREMENT_COMMAND.equals(playgroundCommand)) {
                IncrementCommand command = objectMapper.readValue(body, IncrementCommand.class);

                axonService.sendIncrementCommand(command.getUuid());
            } else {
                LOGGER.warn("Received unhandled command with name: {}", commandName);
            }
        } catch (JMSException | JsonProcessingException exception) {
            LOGGER.error("Couldn't process command.", exception);
        }
    }

}
