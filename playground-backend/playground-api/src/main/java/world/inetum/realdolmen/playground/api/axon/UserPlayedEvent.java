package world.inetum.realdolmen.playground.api.axon;

import java.util.Objects;
import java.util.UUID;

public class UserPlayedEvent {

    private final UUID uuid;
    private final String email;
    private final String country;

    public UserPlayedEvent(UUID uuid, String email, String country) {
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
        UserPlayedEvent that = (UserPlayedEvent) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(email, that.email) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, email, country);
    }

    @Override
    public String toString() {
        return "UserPlayedEvent{" +
                "uuid=" + uuid +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
