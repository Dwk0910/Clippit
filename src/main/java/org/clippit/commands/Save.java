package org.clippit.commands;

import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.clippit.Clippit;
import org.clippit.ClippitException;
import org.clippit.Util;
import org.clippit.annotations.RequiresArgument;

import java.io.File;
import java.io.IOException;

import java.nio.file.Path;

import java.util.Objects;
import java.util.Arrays;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;

@RequiresArgument(count = 2)
public class Save implements Clippit.Command {
    @Override
    public void run(String... argv) {
        File f = new File(argv[0]);
        if (!f.exists()) throw new ClippitException("File does not exist: %s".formatted(argv[0]));

        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(CompressionMethod.DEFLATE);
        parameters.setEncryptFiles(true);
        parameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);

        if (f.isDirectory()) {
            File[] items = Objects.requireNonNull(f.listFiles());
            try (ZipFile zipFile = new ZipFile(Path.of(Clippit.templateDir.toString(), "%s.zip".formatted(argv[1])).toString())) {
                if (items.length == 0 && Util.ask("Directory %s has no any files. Do you want to save empty directory?")) {
                    zipFile.addFolder(f);
                }
                zipFile.addFiles(Arrays.stream(items).toList(), parameters);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("File");
        }
    }
}
