package org.clippit;

import org.clippit.commands.*;

public class Main extends Clippit {
    static {
        // Register commands here
        registerCommand("Help", new Help());
        registerCommand("Save", new Save());
        registerCommand("Load", new Load());
        registerCommand("List", new List());
    }

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                //TODO: TUI interface
                executeCommand("Help", new String[0]);
                return;
            }

            switch (args[0]) {
                case "help" -> executeCommand("Help", args);
                case "save" -> executeCommand("Save", args);
                case "load" -> executeCommand("Load", args);
                case "list" -> executeCommand("List", args);
                default -> System.out.println("%: Not avaliable option.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
