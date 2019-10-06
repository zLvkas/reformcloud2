package systems.reformcloud.reformcloud2.executor.api.common.api.console;

import systems.reformcloud.reformcloud2.executor.api.common.commands.Command;

public interface ConsoleSyncAPI {

    void sendColouredLine(String line) throws IllegalAccessException;

    void sendRawLine(String line);

    String dispatchCommandAndGetResult(String commandLine);

    Command getControllerCommand(String name);

    boolean isControllerCommandRegistered(String name);
}