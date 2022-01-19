package world.inetum.realdolmen.playground.service.persistance.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import world.inetum.realdolmen.playground.service.persistance.entities.UserEntity;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
