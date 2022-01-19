package world.inetum.realdolmen.playground.api.jms;


import java.util.UUID;

public class IncrementCommand {

    private UUID uuid;

    public IncrementCommand() {
    }

    public IncrementCommand(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "IncrementCommand{" +
                "uuid=" + uuid +
                '}';
    }

}
