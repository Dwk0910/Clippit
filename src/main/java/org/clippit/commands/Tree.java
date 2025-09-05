package org.clippit.commands;

import hu.webarticum.treeprinter.printer.listing.ListingTreePrinter;

import org.apache.commons.io.FileUtils;

import org.clippit.Clippit;
import org.clippit.ClippitException;
import org.clippit.util.Util;
import org.clippit.annotations.RequiresArgument;

import net.lingala.zip4j.ZipFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@RequiresArgument
public class Tree implements Clippit.Command {
    @Override
    public void run(String... argv) {
        File template = Util.getTemplate(argv[0]);
        if (template == null) throw new ClippitException("Template '%s': not found.".formatted(argv[0]));

        File extracted = Path.of(Clippit.tempDir.toString(), "tree").toFile();
        try (ZipFile zipFile = new ZipFile(template)) {
            zipFile.extractAll(extracted.getPath());

            if (extracted.listFiles() == null) throw new ClippitException("Template has no files.");

            new ListingTreePrinter().print(Util.buildTree(extracted));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                FileUtils.forceDelete(extracted);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
