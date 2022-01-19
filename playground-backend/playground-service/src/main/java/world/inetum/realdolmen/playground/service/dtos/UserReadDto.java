package world.inetum.realdolmen.playground.service.dtos;

import java.util.UUID;

public class UserReadDto {

    private UUID uuid;
    private String email;
    private String country;
    private int amount;

    public UserReadDto() {
    }

    public UserReadDto(UUID uuid, String email, String country, int amount) {
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

}
