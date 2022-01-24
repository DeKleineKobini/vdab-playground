package world.inetum.realdolmen.playground.service.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.inetum.realdolmen.playground.service.dtos.PlayCreateDto;
import world.inetum.realdolmen.playground.service.dtos.UserIncrementDto;
import world.inetum.realdolmen.playground.service.services.PlayService;

import javax.validation.Valid;

@Api(tags = "Playing")
@RestController
@RequestMapping("play")
public class PlayController {

    private final PlayService playService;

    public PlayController(PlayService playService) {
        this.playService = playService;
    }

    @ApiOperation("With with an \"email\" from a certain \"country.\"")
    @PostMapping
    public void play(@RequestBody @Valid PlayCreateDto dto) {
        playService.play(dto);
    }

    @ApiOperation("Increment someones play counter based on their \"uuid\".")
    @PostMapping("increment")
    public void increment(@RequestBody @Valid UserIncrementDto dto) {
        playService.increment(dto);
    }

}
