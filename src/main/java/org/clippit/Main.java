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
        java.util.List<String> argList = Arrays.stream(args).toList();
        try {
            if (argList.isEmpty()) {
                //TODO: TUI interface
                executeCommand("help", new String[0]);
                return;
            }

            executeCommand(argList.get(0), args);

        } catch (IllegalArgumentException e) {
            System.out.println("Clippit Error: " + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        } catch (RuntimeException e) {
            System.out.printf("Clippit: %s: Not avaliable option.%n", argList.get(0));
        }
    }
}
