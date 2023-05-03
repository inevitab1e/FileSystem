package handler.executor;

import context.ExecutorContext;
import io.StandardInput;
import io.StandardOutput;

public class PrintWorkDirectoryExecutor implements Executor{

    String executorName = "pwd";

    @Override
    public StandardOutput execute(StandardInput input, ExecutorContext context) {
        return new StandardOutput(this.executorName).write(context.path);
    }
}
