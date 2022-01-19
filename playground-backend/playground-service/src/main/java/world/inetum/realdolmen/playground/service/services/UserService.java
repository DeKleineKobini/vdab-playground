package world.inetum.realdolmen.playground.service.services;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.playground.service.axon.queries.FindUsersQuery;
import world.inetum.realdolmen.playground.service.persistance.entities.UserEntity;

import java.util.List;

@Service
public class UserService {

    private final QueryGateway queryGateway;

    public UserService(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    public List<UserEntity> getUsers() {
        return queryGateway.query(
                        new FindUsersQuery(),
                        ResponseTypes.multipleInstancesOf(UserEntity.class))
                .join();
    }

}
