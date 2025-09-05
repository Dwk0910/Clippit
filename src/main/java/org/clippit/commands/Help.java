package org.clippit.commands;

import org.clippit.Clippit;

public class Help implements Clippit.Command {
    @Override
    public void run(String... argv) {
        System.out.println("""
                [ Clippit Help ]
                
                Command: cl <COMMANDS/FLAGS>
                Example: cl save template .
                
                FLAGS:
                --v : Show current Clippit version.
                
                COMMANDS:
                
                save <TEMPLATE NAME> <TEMPLATE LOCATION> : Make new template.
                    <TEMPLATE NAME> : name of new template
                    <TEMPLATE LOCATION> : location of new template's original location
                
                load <TEMPLATE NAME> <TEMPLATE LOCATION(Optional)> : Load saved template.
                    <TEMPLATE NAME> : name of target template.
                    <TEMPLATE LOCATION> : load location (Optional, Default: '.')
                
                remove <TEMPLATE NAME> : Delete template.
                    <TEMPLATE NAME> : name of target template
                
                tree <TEMPLATE NAME> : Show filetree of template.
                    <TEMPLATE NAME> : name of target template
                
                list : Show all templates.
                
                help : Show this.
                """);
    }
}
