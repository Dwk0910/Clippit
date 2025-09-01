package org.clippit;

import org.clippit.commands.*;

public class Main extends Clippit {
    static {
        // Register commands here
        registerCommand("Help", new Help());
        registerCommand("Save", new Save());
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
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
