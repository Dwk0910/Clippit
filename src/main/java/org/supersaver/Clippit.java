package org.supersaver;

import java.util.HashMap;
import java.util.Map;

public class Clippit {
    private static final Map<String, CommandInfo> commandMap = new HashMap<>();

    private record CommandInfo(int requiredParameter, Command command) {
    }

    public interface Command {
        void run(String[] args);
    }

    public static void registerCommand(String name, int requiredParamter, Command run) {
        commandMap.put(name, new CommandInfo(requiredParamter, run));
    }

    public static void executeCommand(String name, String[] args) {
        CommandInfo cmdinf = commandMap.get(name);
        if (cmdinf == null) throw new RuntimeException("[Please report this to developer] Unknown command: " + name);
        else if (args.length >= cmdinf.requiredParameter) cmdinf.command.run(args);
        else
            throw new IllegalArgumentException("Required argument count: %s, Given: %s".formatted(cmdinf.requiredParameter, args.length));
    }
}
