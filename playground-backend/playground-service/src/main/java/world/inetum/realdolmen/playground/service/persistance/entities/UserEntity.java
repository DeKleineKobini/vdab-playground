package world.inetum.realdolmen.playground.service.persistance.entities;

import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Audited
@Entity
public class UserEntity {

    @Id
    private UUID uuid;

    private String email;
    private String country;
    @Column(nullable = false)
    private int amount = 0;

    public UserEntity() {
    }

    public UserEntity(UUID uuid, String email, String country) {
        this(uuid, email, country, 0);
    }

    public UserEntity(UUID uuid, String email, String country, int amount) {
        this.uuid = uuid;
        this.email = email;
        this.country = country;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return amount == that.amount && Objects.equals(uuid, that.uuid) && Objects.equals(email, that.email) && Objects.equals(country, that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, email, country, amount);
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "uuid=" + uuid +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                ", amount=" + amount +
                '}';
    }

}
