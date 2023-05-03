package handler;

import context.ExecutorContext;
import handler.executor.*;
import io.StandardInput;
import io.StandardOutput;

import java.util.HashMap;

/**
 * Choose which executor should be used to handle the command
 */
public class EventSelector {

    HashMap<String, Executor> executable = new HashMap<>();

    public EventSelector() {
        //TODO dynamically generated executor and add it into set
        executable.put("mkdir", new CreateDirectoryExecutor());
        executable.put("cd", new ChangeDirectoryExecutor());
        executable.put("touch", new CreateFileExecutor());
        executable.put("ls", new ListFilesExecutor());
        executable.put("pwd", new PrintWorkDirectoryExecutor());
        executable.put("rmdir", new RemoveDirectoryExecutor());
        executable.put("rm", new RemoveFileExecutor());
    }

    public StandardOutput select(StandardInput input, ExecutorContext context) {
        if (executable.containsKey(input.commandName)) {
            final Executor executor = executable.get(input.commandName);
            return execute(executor, input, context);
        } else {
            return new StandardOutput("bash").write("no such command");
        }
    }

    public StandardOutput execute(Executor executor, StandardInput input, ExecutorContext context) {
        return executor.execute(input, context);
    }
}
