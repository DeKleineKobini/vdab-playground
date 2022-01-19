package world.inetum.realdolmen.playground.service.persistance.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class UserEntity {

    @Id
    private UUID uuid;

    private String email;
    private String country;

    public UserEntity() {
    }

    public UserEntity(UUID uuid, String email, String country) {
        this.uuid = uuid;
        this.email = email;
        this.country = country;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return Objects.equals(uuid, that.uuid) && Objects.equals(email, that.email) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, email, country);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "uuid=" + uuid +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
