package world.inetum.realdolmen.playground.api.jms;

import javax.jms.JMSException;
import javax.jms.Message;

public class JmsUtil {

    public static final String PROPERTY_NAME_COMMAND_NAME = "commandName";

    private JmsUtil() {
    }

    public static void setJmsHeaders(String commandName, Message message) throws JMSException {
        message.setStringProperty(PROPERTY_NAME_COMMAND_NAME, commandName);
    }

    public static String getCommandName(Message message) throws JMSException {
        return message.getStringProperty(PROPERTY_NAME_COMMAND_NAME);
    }

}
