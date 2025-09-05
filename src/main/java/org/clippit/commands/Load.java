package org.clippit.commands;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.FileHeader;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import org.clippit.Clippit;
import org.clippit.ClippitException;
import org.clippit.ColorText;
import org.clippit.util.Util;
import org.clippit.annotations.RequiresArgument;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

@RequiresArgument
public class Load implements Clippit.Command {
    @Override
    public void run(String... argv) {
        try {
            File template = Util.getTemplate(argv[0]);
            if (template == null) throw new ClippitException("%s: Template not exist.".formatted(argv[0]));

            File targetFolder = new File(".");
            if (argv.length >= 2) targetFolder = new File(argv[1]);

            if (!targetFolder.exists() && Util.ask("Target folder '%s' does not exist. Do you want to create a new directrory?".formatted(argv[1])))
                FileUtils.forceMkdir(targetFolder);
            else if (!targetFolder.isDirectory())
                throw new ClippitException("'%s' is not a directory".formatted(argv[1]));

            try (ZipFile zipFile = new ZipFile(template)) {
                File[] fileList = targetFolder.listFiles();
                if (fileList != null && fileList.length > 0) {
                    java.util.List<String> original_names = new ArrayList<>();
                    Arrays.stream(fileList).forEach(file -> original_names.add(FilenameUtils.getName(file.toPath().toString())));

                    java.util.List<String> conflicts = new ArrayList<>();
                    for (FileHeader i : zipFile.getFileHeaders()) {
                        if (original_names.contains(i.getFileName()))
                            conflicts.add(i.getFileName());
                    }

                    if (!conflicts.isEmpty()) {
                        System.out.println(ColorText.text("Following files have conflicts:", "", "none", true, false, false));
                        for (String name : conflicts) {
                            System.out.println("- " + name);
                        }
                        if (!Util.ask("Overwrite?")) throw new ClippitException("Action cancelled.");
                    }
                }
                zipFile.extractAll(targetFolder.getPath());
            }

            System.out.printf("Template '%s' has been successfully loaded into directory '%s'.%n", argv[0], targetFolder.getPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
