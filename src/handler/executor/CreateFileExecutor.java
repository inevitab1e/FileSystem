package handler.executor;

import context.ExecutorContext;
import core.DirectoryNode;
import core.FileNode;
import core.Node;
import io.StandardInput;
import io.StandardOutput;

import java.time.LocalDateTime;
import java.util.HashMap;

public class CreateFileExecutor implements Executor{

    String executorName = "touch";

    @Override
    public StandardOutput execute(StandardInput input, ExecutorContext context) {
        StandardOutput output = new StandardOutput(this.executorName);
        String param = input.params.get(0);

        if (context.currentNode instanceof DirectoryNode) {
            HashMap<String, Node> children = ((DirectoryNode) context.currentNode).children;
            if (param.contains("/") || children.containsKey(param)) {
                output.write(output.source).write(": ").write("an illegal name ").write(param);
            } else {
                // new a File node
                FileNode fileNode = new FileNode(param, 0, context.user, LocalDateTime.now(), context.currentNode);
                // link it to the file system tree
                ((DirectoryNode) context.currentNode).children.put(param, fileNode);
                context.currentNode.size++;
            }
        } else {
            output.write(output.source).write(": ").write("current is not a directory");
        }
        return output;
    }
}
