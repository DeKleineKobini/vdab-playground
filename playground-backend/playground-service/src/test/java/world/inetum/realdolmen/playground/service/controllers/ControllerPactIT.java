package world.inetum.realdolmen.playground.service.controllers;

import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.spring.junit5.PactVerificationSpringProvider;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import world.inetum.realdolmen.playground.service.persistance.entities.UserEntity;
import world.inetum.realdolmen.playground.service.services.PlayService;
import world.inetum.realdolmen.playground.service.services.UserService;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
@Provider("playground-rest")
@PactFolder("pacts")
@MockBean(PlayService.class)
class ControllerPactIT {

    @MockBean
    private UserService userService;

    @State("Default success behavior")
    public void defaultState() {
        when(userService.getUsers()).thenReturn(List.of(
                new UserEntity(UUID.fromString("91d29407-82fb-48ef-8cb3-de61765a09a4"), "test@vdab.be", "be", 18)
        ));
    }

    @TestTemplate
    @ExtendWith(PactVerificationSpringProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

}
