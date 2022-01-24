package world.inetum.realdolmen.playground.api.jms;

import world.inetum.realdolmen.playground.api.IncrementCommand;
import world.inetum.realdolmen.playground.api.PlayCommand;

import java.util.Arrays;
import java.util.function.Predicate;

public enum PlaygroundCommand {

    PLAY_COMMAND("PLAY_COMMAND", o -> o instanceof PlayCommand),
    INCREMENT_COMMAND("INCREMENT_COMMAND", o -> o instanceof IncrementCommand),
    ;

    private final String commandName;
    private final Predicate<Object> matches;

    PlaygroundCommand(String commandName, Predicate<Object> matches) {
        this.commandName = commandName;

        this.matches = matches;
    }

    public static PlaygroundCommand of(Object object) {
        return Arrays.stream(values())
                .filter(command -> command.matches.test(object))
                .findFirst()
                .orElse(null);
    }

    public static PlaygroundCommand ofName(String commandName) {
        return Arrays.stream(values())
                .filter(command -> command.commandName.equals(commandName))
                .findFirst()
                .orElse(null);
    }

    public String getCommandName() {
        return commandName;
    }

}
