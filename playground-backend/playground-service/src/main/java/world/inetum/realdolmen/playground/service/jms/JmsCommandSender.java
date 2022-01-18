package world.inetum.realdolmen.playground.service.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import world.inetum.realdolmen.playground.api.jms.JmsUtil;
import world.inetum.realdolmen.playground.api.jms.PlayCommand;
import world.inetum.realdolmen.playground.api.jms.PlaygroundCommand;
import world.inetum.realdolmen.playground.service.config.properties.JmsProperties;

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
        try {
            PlayCommand command = new PlayCommand(email, country);

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
