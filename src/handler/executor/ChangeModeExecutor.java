package handler.executor;

import context.ExecutorContext;
import core.DirectoryNode;
import core.Node;
import io.StandardInput;
import io.StandardOutput;

import java.util.HashMap;
import java.util.List;

public class ChangeModeExecutor implements Executor{

    String executorName = "chmod";

    @Override
    public StandardOutput execute(StandardInput input, ExecutorContext context) {
        StandardOutput output = new StandardOutput(this.executorName);
        final List<String> params = input.params;
        final String newMod = params.get(0);
        final String targetNode = params.get(1);

        int currentUser = newMod.charAt(0) - '0';
        int currentGroup = newMod.charAt(1) - '0';
        int others = newMod.charAt(2) - '0';

        // check the mode number
        if (currentUser > 7 || currentUser < 1
                || currentGroup > 7 || currentGroup < 1
                || others > 7 || others < 1) {
            output.write(output.source).write(": ").write("param is illegal");
            return output;
        }

        //check if the node exists
        if (context.currentNode instanceof DirectoryNode) {
            final HashMap<String, Node> children = ((DirectoryNode) context.currentNode).children;
            if (children.containsKey(targetNode)) {
                // node exists
                context.currentNode.permission[0] = currentUser;
                context.currentNode.permission[1] = currentGroup;
                context.currentNode.permission[2] = others;
            }else {
                output.write(output.source).write(": ").write("no such file or directory");
            }
        } else {
            output.write(output.source).write(": ").write("current is not directory");
        }

        return output;
    }
}
