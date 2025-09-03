package org.clippit;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Util {
    public static boolean ask(String question) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print(ColorText.text(question + " (yes/no): ", "yellow", "none", true, false, false));
            String input = reader.readLine();
            return input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
