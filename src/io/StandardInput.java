package io;

import java.util.ArrayList;
import java.util.List;

public class StandardInput {

    public String commandName;
    public List<String> options = new ArrayList<>();
    public List<String> params = new ArrayList<>();

    public StandardInput(String commandName, List<String> options, List<String> params) {
        this.commandName = commandName;
        this.options = options;
        this.params = params;
    }

    public StandardInput(String wholeCommand) {
        final String[] split = wholeCommand.split("\\s+");
        assert split.length >= 1;
        this.commandName = split[0];
        for (int i = 1; i < split.length; i++) {
            String ops = split[i];
            if (ops.startsWith("-")) {
                options.add(ops.substring(1));
            } else {
                params.add(ops);
            }
        }
    }
}
