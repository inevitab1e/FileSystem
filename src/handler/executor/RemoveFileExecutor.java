package handler.executor;

import context.ExecutorContext;
import core.DirectoryNode;
import core.Node;
import io.StandardInput;
import io.StandardOutput;

import java.util.HashMap;

public class RemoveFileExecutor implements Executor {

    String executorName = "rm";

    @Override
    public StandardOutput execute(StandardInput input, ExecutorContext context) {
        StandardOutput output = new StandardOutput(this.executorName);
        String fileName = input.params.get(0);

        if (context.currentNode instanceof DirectoryNode) {
            HashMap<String, Node> children = ((DirectoryNode) context.currentNode).children;
            if (!children.containsKey(fileName)) {
                output.write(output.source).write(": ").write("no such file");
                return output;
            }
            if (children.get(fileName).isDirectory) {
                output.write(output.source).write(": ").write("that is a directory");
                return output;
            }

            children.remove(fileName);
        }
        return output;
    }
}
