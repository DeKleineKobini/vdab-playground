package world.inetum.realdolmen.playground.relay.axon;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;
import java.util.UUID;

public class UserPlayCommand {

    @TargetAggregateIdentifier
    private final UUID uuid;
    private final String email;
    private final String country;

    public UserPlayCommand(UUID uuid, String email, String country) {
        this.uuid = uuid;
        this.email = email;
        this.country = country;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPlayCommand that = (UserPlayCommand) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(email, that.email) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, email, country);
    }

    @Override
    public String toString() {
        return "UserPlayCommand{" +
                "uuid=" + uuid +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
