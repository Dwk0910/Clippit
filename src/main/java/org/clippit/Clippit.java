package org.clippit;

import org.clippit.annotations.RequiresArgument;

import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Clippit {
    public static Path datDir = Path.of(System.getProperty("user.home"), ".clippit");
    public static Path tempDir = Path.of(System.getProperty("java.io.tmpdir"), "clippit");
    public static Path templateDir = Path.of(datDir.toAbsolutePath().toString(), "templates");

    static {
        // Create essential directories
        if (!datDir.toFile().exists()) if (!datDir.toFile().mkdirs())
            throw new Error("Unable to create directory " + datDir.toAbsolutePath());
        if (!templateDir.toFile().exists()) if (!templateDir.toFile().mkdirs())
            throw new Error("Unable to create directory " + templateDir.toAbsolutePath());
        if (!tempDir.toFile().exists()) if (!tempDir.toFile().mkdirs())
            throw new Error("Unable to create directory " + tempDir.toAbsolutePath());
    }

    private static final List<CommandInfo> commandMap = new ArrayList<>();

    private record CommandInfo(int requiredParameter, Command command, List<String> aliases) {
    }

    public interface Command {
        void run(String[] args);
    }

    public static void registerCommand(Command run, String... aliases) {
        int requiredParameter = 0;
        Class<? extends Command> class_ = run.getClass();
        if (class_.isAnnotationPresent(RequiresArgument.class)) {
            RequiresArgument annotation = class_.getAnnotation(RequiresArgument.class);
            requiredParameter = annotation.count();
        }

        commandMap.add(new CommandInfo(requiredParameter, run, Arrays.asList(aliases)));
    }

    private static volatile CommandInfo cmdinf = null;

    public static void executeCommand(String name, String[] args) {
        for (CommandInfo item : commandMap) {
            item.aliases.forEach(alias -> {
                if (alias.equalsIgnoreCase(name)) {
                    cmdinf = item;
                }
            });
        }

        if (cmdinf == null)
            throw new ClippitException("Clippit: Unknown command: " + name);
        else if (cmdinf.requiredParameter == 0) cmdinf.command.run(null);
        else if (args.length >= cmdinf.requiredParameter) cmdinf.command.run(args);
        else
            throw new ClippitException.LackOfArgumentException("Clippit: Required argument count: %s, Given: %s".formatted(cmdinf.requiredParameter, args.length));
    }
}
