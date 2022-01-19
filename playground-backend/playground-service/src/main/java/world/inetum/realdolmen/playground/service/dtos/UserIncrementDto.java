package world.inetum.realdolmen.playground.service.dtos;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class UserIncrementDto {

    @NotNull
    private UUID uuid;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

}
