package org.clippit.commands;

import org.clippit.Util;
import org.clippit.Clippit;
import org.clippit.ClippitException;
import org.clippit.annotations.RequiresArgument;

import java.io.File;
import java.io.IOException;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

import java.nio.file.Paths;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.exception.ZipException;

@RequiresArgument(count = 2)
public class Save implements Clippit.Command {
    @Override
    public void run(String... argv) {
        Path path = Paths.get(argv[1]);
        if (!path.toFile().exists()) throw new ClippitException("%s: Not valid file or directory.".formatted(argv[1]));
        if (Util.isExist(argv[0])) throw new ClippitException("%s: Template already exists.".formatted(argv[0]));

        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(path);
             ZipFile zipFile = new ZipFile(Path.of(Clippit.templateDir.toString(), argv[0] + ".zip").toString())
        ) {
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(CompressionMethod.DEFLATE);
            dirStream.forEach(path_ -> {
                try {
                    File f = path_.toFile();
                    if (f.isDirectory()) zipFile.addFolder(f, parameters);
                    else zipFile.addFile(f, parameters);
                } catch (ZipException e) {
                    System.out.println(e.getMessage());
                }
            });
            System.out.printf("%s: Template successfully created.%n", argv[0]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
