package handler.executor;

import context.ExecutorContext;
import core.DirectoryNode;
import core.FileNode;
import core.Node;
import io.StandardInput;
import io.StandardOutput;

import java.util.HashMap;

/**
 * cd [directory]
 */
public class ChangeDirectoryExecutor implements Executor {

    String executorName = "cd";

    @Override
    public StandardOutput execute(StandardInput input, ExecutorContext context) {
        StandardOutput output = new StandardOutput(this.executorName);
        final String param = input.params.get(0);

        switch (param) {
            case "/":
                context.currentNode = context.root;
                context.path = "/";
                break;
            case ".":
                break;
            case "..": {
                if (context.currentNode != context.root) {
                    context.currentNode = context.currentNode.parent;
                    StringBuilder newPath = new StringBuilder();
                    final String[] split = context.path.split("/");
                    for (int i = 0; i < split.length - 1; i++) {
                        newPath.append(split[i]).append("/");
                    }
                    context.path = newPath.toString();
                }
                break;
            }
            default: {
                Node cNode;
                final String[] split;
                String newPath;
                boolean flag = true;

                if (param.startsWith("/")) {
                    cNode = context.root; // absolute path start from /
                    split = param.substring(1).split("/");
                    newPath = "/";
                } else {
                    cNode = context.currentNode; // relative path start from current node
                    split = param.split("/");
                    newPath = context.path;
                }

                for (String s : split) {
                    if (cNode instanceof DirectoryNode) {
                        HashMap<String, Node> children = ((DirectoryNode) cNode).children;
                        if (children.containsKey(s)) {
                            cNode = children.get(s);
                            if (cNode instanceof FileNode) {
                                output.write(output.source).write(": ").write("it is not a directory");
                                flag = false;
                                break;
                            }
                            newPath += (cNode.name + "/");
                        } else {
                            output.write(output.source).write(": ").write("no such file or directory");
                            flag = false;
                            break;
                        }
                    } else if (cNode instanceof FileNode) {
                        output.write(output.source).write(": ").write(cNode.name).write(" is not a directory");
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    context.currentNode = cNode;
                    context.path = newPath;
                }
                break;
            }
        }
        return output;
    }
}
