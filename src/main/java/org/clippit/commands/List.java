package org.clippit.commands;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import de.vandermeer.asciitable.AsciiTable;
import de.vandermeer.skb.interfaces.transformers.textformat.TextAlignment;

import org.clippit.Clippit;
import org.clippit.util.Util;

import java.io.File;

import java.text.SimpleDateFormat;

public class List implements Clippit.Command {
    @Override
    public void run(String... argv) {
        java.util.List<File> templates = Util.getTemplates();
        if (templates.isEmpty()) System.out.println("No templates found.");
        else {
            final boolean overOne = templates.size() > 1;
            long totalSize = 0L;
            System.out.println("There " + ((overOne) ? "are " : "is ") + templates.size() + ((overOne) ? " templates:" : " template:"));
            AsciiTable at = new AsciiTable();
            at.addRule();
            at.addRow("NAME", "CREATED AT", "SIZE").setTextAlignment(TextAlignment.CENTER);
            at.addRule();
            SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss");
            for (File f : templates) {
                at.addRow(FilenameUtils.getBaseName(f.getName()), df.format(f.lastModified()), Util.getReadableSize(FileUtils.sizeOf(f)));
                totalSize += FileUtils.sizeOf(f);
            }
            at.addRule();
            System.out.println(at.render());
            System.out.println("Total size : " + Util.getReadableSize(totalSize));
        }
    }
}
