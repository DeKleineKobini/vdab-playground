package world.inetum.realdolmen.playground.api.axon;

import java.util.Objects;
import java.util.UUID;

public class UserIncrementedEvent {

    private final UUID uuid;

    public UserIncrementedEvent(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserIncrementedEvent that = (UserIncrementedEvent) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    @Override
    public String toString() {
        return "UserIncrementEvent{" +
                "uuid=" + uuid +
                '}';
    }

}
