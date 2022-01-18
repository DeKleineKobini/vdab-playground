package world.inetum.realdolmen.playground.service.axon;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.Objects;

public class UserPlayCommand {

    @TargetAggregateIdentifier
    private final String email;
    private final String country;

    public UserPlayCommand(String email, String country) {
        this.email = email;
        this.country = country;
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
        return Objects.equals(email, that.email) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, country);
    }

    @Override
    public String toString() {
        return "UserPlayCommand{" +
                "email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
