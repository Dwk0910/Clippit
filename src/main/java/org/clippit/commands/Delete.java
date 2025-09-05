package org.clippit.commands;

import org.apache.commons.io.FileUtils;
import org.clippit.Clippit;
import org.clippit.ClippitException;
import org.clippit.Util;
import org.clippit.annotations.RequiresArgument;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@RequiresArgument
public class Delete implements Clippit.Command {
    @Override
    public void run(String... argv) {
        if (!Util.isExist(argv[0])) throw new ClippitException("Not exist: " + argv[0]);
        try {
            File f = new File(Path.of(Clippit.templateDir.toString(), argv[0] + ".zip").toString());
            FileUtils.delete(f);
            System.out.printf("Template %s has been deleted successfully.%n", argv[0]);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
