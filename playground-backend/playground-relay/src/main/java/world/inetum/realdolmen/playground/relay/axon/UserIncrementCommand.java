package world.inetum.realdolmen.playground.relay.axon;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;
import java.util.UUID;

public class UserIncrementCommand {

    @TargetAggregateIdentifier
    private final UUID uuid;

    public UserIncrementCommand(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIncrementCommand that = (UserIncrementCommand) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "UserIncrementCommand{" +
                "uuid=" + uuid +
                '}';
    }

}
