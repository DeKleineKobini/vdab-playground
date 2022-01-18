package world.inetum.realdolmen.playground.service.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jms")
public class JmsProperties {

    private String queue = "playground.queue";

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

}
