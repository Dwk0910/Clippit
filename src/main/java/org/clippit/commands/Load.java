package org.clippit.commands;

import net.lingala.zip4j.ZipFile;

import org.apache.commons.io.FileUtils;

import org.clippit.Clippit;
import org.clippit.ClippitException;
import org.clippit.Util;
import org.clippit.annotations.RequiresArgument;

import java.io.File;
import java.io.IOException;

@RequiresArgument
public class Load implements Clippit.Command {
    @Override
    public void run(String... argv) {
        try {
            File template = Util.getTemplate(argv[0]);
            if (template == null) throw new ClippitException("Template %s is not exist.".formatted(argv[0]));

            File targetFolder = new File(argv[1]);
            if (!targetFolder.exists() && Util.ask("Target folder '%s' is not exist. Do you want to create a new directrory?".formatted(argv[1])))
                FileUtils.forceMkdir(targetFolder);
            else if (!targetFolder.isDirectory()) throw new ClippitException("Target folder is not a directory");

            try (ZipFile zipFile = new ZipFile(template)) {
                zipFile.extractAll(targetFolder.getAbsolutePath());
            }

            System.out.printf("Template '%s' has been successfully loaded into directory '%s'.%n", argv[0], targetFolder.getPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
