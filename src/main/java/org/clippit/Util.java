package org.clippit;

import org.apache.commons.io.FileUtils;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.nio.file.Path;

public class Util {
    private static void doRecovery() {
        try {
            File f = Clippit.templateDir.toFile();
            if (!f.exists()) FileUtils.touch(f);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

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

    public static boolean isExist(String name) {
        doRecovery();
        File f = new File(Path.of(Clippit.templateDir.toString(), name + ".zip").toString());
        if (!f.exists()) return false;
        return f.exists();
    }

    public static List<File> getTemplates() {
        List<File> result = new ArrayList<>();
        File f = Clippit.templateDir.toFile();
        if (!f.exists()) {
            doRecovery();
            return result;
        }

        File[] templates = f.listFiles();
        if (templates == null) return result;

        for (File item : templates) {
            if (item.getName().endsWith(".zip")) result.add(item);
        }
        return result;
    }

    public static String getReadableSize(long size) {
        String[] units = {"B", "KB", "MB", "GB", "TB", "PB"};
        if (size < 1000) return size + " B";

        int unitIndex = 0;
        double displaySize = size;

        while (displaySize >= 1000 && unitIndex < units.length - 1) {
            displaySize /= 1000.0;
            unitIndex++;
        }

        return String.format("%.1f %s", displaySize, units[unitIndex]);
    }
}
