package org.clippit;

import java.util.Arrays;

import org.clippit.commands.*;

public class Main extends Clippit {
    static {
        // Register commands here
        registerCommand("help", new Help());
        registerCommand("save", new Save());
        registerCommand("load", new Load());
        registerCommand("list", new List());
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
