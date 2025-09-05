package org.clippit;

import java.util.Arrays;

import org.clippit.commands.*;

public class Main extends Clippit {
    static {
        // Register commands here
        registerCommand(new Help(), "help");
        registerCommand(new Save(), "save");
        registerCommand(new Load(), "load");
        registerCommand(new List(), "list");
        registerCommand(new Tree(), "tree");
        registerCommand(new Delete(), "remove", "delete");
    }

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                //TODO: TUI interface
                executeCommand("help", new String[0]);
                return;
            }

            executeCommand(args[0], Arrays.copyOfRange(args, 1, args.length, String[].class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
