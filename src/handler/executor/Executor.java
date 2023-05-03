package handler.executor;

import context.ExecutorContext;
import io.StandardInput;
import io.StandardOutput;


public interface Executor {

    String executorName = "";

    StandardOutput execute(StandardInput input, ExecutorContext context);

    default String getCommand() {
        return this.executorName;
    }
}
