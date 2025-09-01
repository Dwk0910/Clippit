package org.clippit;

import java.nio.file.Path;

import java.util.HashMap;
import java.util.Map;

public class Clippit {
    public static Path datDir = Path.of(System.getProperty("user.home"), ".clippit");
    public static Path templateDir = Path.of(datDir.toAbsolutePath().toString(), "templates");

    static {
        // Create essential directories
        if (!datDir.toFile().exists()) if (!datDir.toFile().mkdirs())
            throw new RuntimeException("Unable to create directory " + datDir.toAbsolutePath());
        else if (!templateDir.toFile().exists()) if (!templateDir.toFile().mkdirs())
            throw new RuntimeException("Unable to create directory " + templateDir.toAbsolutePath());
    }

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
        if (cmdinf == null)
            throw new RuntimeException("[Please report this error to developer ] Unknown command: " + name);
        else if (args.length >= cmdinf.requiredParameter) cmdinf.command.run(args);
        else
            throw new IllegalArgumentException("Required argument count: %s, Given: %s".formatted(cmdinf.requiredParameter, args.length));
    }
}
