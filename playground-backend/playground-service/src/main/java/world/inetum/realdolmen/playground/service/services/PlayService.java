package world.inetum.realdolmen.playground.service.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import world.inetum.realdolmen.playground.service.dtos.PlayCreateDto;
import world.inetum.realdolmen.playground.service.jms.JmsCommandSender;

@Service
public class PlayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayService.class);

    private final JmsCommandSender jmsCommandSender;


    public PlayService(JmsCommandSender jmsCommandSender) {
        this.jmsCommandSender = jmsCommandSender;
    }

    public void play(PlayCreateDto dto) {
        LOGGER.info("Played by '{}' from '{}'!", dto.getEmail(), dto.getCountry());
        jmsCommandSender.play(dto.getEmail(), dto.getCountry());
    }

}
