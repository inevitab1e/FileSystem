package handler.executor;

import context.ExecutorContext;
import core.DirectoryNode;
import core.Node;
import io.StandardInput;
import io.StandardOutput;

import java.util.HashMap;

public class RemoveDirectoryExecutor implements Executor{

    String executorName = "rmdir";

    @Override
    public StandardOutput execute(StandardInput input, ExecutorContext context) {
        StandardOutput output = new StandardOutput(this.executorName);
        String directoryName = input.params.get(0);

        if (context.currentNode instanceof DirectoryNode) {
            HashMap<String, Node> children = ((DirectoryNode) context.currentNode).children;
            if (!children.containsKey(directoryName)) {
                output.write(output.source).write(": ").write("no such directory");
                return output;
            }
            if (!children.get(directoryName).isDirectory) {
                output.write(output.source).write(": ").write("that is a file");
                return output;
            }

            children.remove(directoryName);
        }
        return output;
    }
}
