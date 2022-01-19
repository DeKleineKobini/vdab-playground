package world.inetum.realdolmen.playground.service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.inetum.realdolmen.playground.service.dtos.UserReadDto;
import world.inetum.realdolmen.playground.service.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserReadDto> getUsers() {
        return userService.getUsers()
                .stream()
                .map((user) -> new UserReadDto(user.getUuid(), user.getEmail(), user.getCountry(), user.getAmount()))
                .collect(Collectors.toList());
    }

}
