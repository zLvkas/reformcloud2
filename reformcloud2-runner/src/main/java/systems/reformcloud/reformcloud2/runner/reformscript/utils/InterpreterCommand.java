package systems.reformcloud.reformcloud2.runner.reformscript.utils;

import systems.reformcloud.reformcloud2.runner.reformscript.InterpretedReformScript;

import javax.annotation.Nonnull;
import java.util.Collection;

/**
 * Represents a command which can get executed from a reform script
 */
public abstract class InterpreterCommand {

    /**
     * Creates new interpreter command
     *
     * @param command The name of the command
     */
    public InterpreterCommand(@Nonnull String command) {
        this.command = command.toUpperCase();
    }

    private final String command;

    /**
     * @return The actual command name which is always upper-case
     */
    @Nonnull
    public String getCommand() {
        return command;
    }

    /**
     * Executes the current command
     *
     * @param cursorLine The current line of the cursor
     * @param script     The script from which the command got executed
     * @param allLines   All lines of the script
     */
    public abstract void execute(@Nonnull String cursorLine, @Nonnull InterpretedReformScript script, @Nonnull Collection<String> allLines);
}
