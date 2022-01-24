package world.inetum.realdolmen.playground.service.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import world.inetum.realdolmen.playground.api.IncrementCommand;
import world.inetum.realdolmen.playground.api.PlayCommand;
import world.inetum.realdolmen.playground.api.jms.JmsUtil;
import world.inetum.realdolmen.playground.api.jms.PlaygroundCommand;
import world.inetum.realdolmen.playground.service.config.properties.JmsProperties;

import java.util.UUID;

@Component
public class JmsCommandSender {

    private final JmsProperties jmsProperties;
    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public JmsCommandSender(JmsProperties jmsProperties, JmsTemplate jmsTemplate, ObjectMapper objectMapper) {
        this.jmsProperties = jmsProperties;
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;

    }

    public void play(String email, String country) {
        PlayCommand command = new PlayCommand()
                .email(email)
                .country(country);

        sendCommand(command);
    }

    public void increment(UUID uuid) {
        IncrementCommand command = new IncrementCommand()
                .uuid(uuid);

        sendCommand(command);
    }

    private void sendCommand(Object command) {
        try {
            final PlaygroundCommand playgroundCommand = PlaygroundCommand.of(command);

            jmsTemplate.convertAndSend(
                    jmsProperties.getQueue(),
                    objectMapper.writeValueAsString(command),
                    message -> {
                        JmsUtil.setJmsHeaders(playgroundCommand.getCommandName(), message);
                        return message;
                    }
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
