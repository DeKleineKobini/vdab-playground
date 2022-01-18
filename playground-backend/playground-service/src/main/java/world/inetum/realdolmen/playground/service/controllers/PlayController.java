package world.inetum.realdolmen.playground.service.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.inetum.realdolmen.playground.service.dtos.PlayCreateDto;
import world.inetum.realdolmen.playground.service.services.PlayService;

import javax.validation.Valid;

@RestController
@RequestMapping("play")
public class PlayController {

    private final PlayService playService;

    public PlayController(PlayService playService) {
        this.playService = playService;
    }

    @PostMapping
    public void play(@RequestBody @Valid PlayCreateDto dto) {
        playService.play(dto);
    }

}
