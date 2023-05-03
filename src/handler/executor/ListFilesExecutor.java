package handler.executor;

import context.ExecutorContext;
import core.DirectoryNode;
import core.Node;
import io.StandardInput;
import io.StandardOutput;

import java.util.HashMap;

public class ListFilesExecutor implements Executor {

    String executorName = "ls";

    @Override
    public StandardOutput execute(StandardInput input, ExecutorContext context) {
        StandardOutput output = new StandardOutput(this.executorName);
        //TODO that is not a appropriate way to handle this
        String ops = "";
        if (input.options.size() > 0) {
            ops = input.options.get(0);
        }
        if ("f".equals(ops)) {
            output = listDetail(context);
        } else {
            output = list(context);
        }
        return output;
    }

    public StandardOutput list(ExecutorContext context) {
        StandardOutput output = new StandardOutput(this.executorName);
        if ( context.currentNode instanceof DirectoryNode) {
            final HashMap<String, Node> children = ((DirectoryNode) context.currentNode).children;
            for (Node n : children.values()) {
                output.write(n.name).write(" ");
            }
        }
        return output;
    }

    public StandardOutput listDetail(ExecutorContext context) {
        StandardOutput output = new StandardOutput(this.executorName);
        if ( context.currentNode instanceof DirectoryNode) {
            final HashMap<String, Node> children = ((DirectoryNode) context.currentNode).children;
            for (Node n : children.values()) {
                // isDirectory ?
                if (n.isDirectory) {
                    output.write("d");
                } else {
                    output.write("-");
                }

                // permission
                int[] permission = n.permission;
                for (int i = 0; i < 3; i++) {
                    int p = permission[i];

                    //read
                    if (p - 4 >= 0) {
                        p -= 4;
                        output.write("r");
                    } else {
                        output.write("-");
                    }

                    //write
                    if ( p - 2 >= 0) {
                        p -= 2;
                        output.write("w");
                    } else {
                        output.write("-");
                    }

                    //execute
                    if (p-1 >= 0) {
                        p -= 1;
                        output.write("x");
                    } else {
                        output.write("-");
                    }

                }

                // owner
                output.write("     ");
                output.write(n.owner);

                // size
                output.write("     ");
                output.write(String.valueOf(n.size));

                // time
                output.write("     ");
                output.write(n.timestamp.toString());

                // name
                output.write("     ");
                output.write(n.name);

                output.write("\n");
            }
        }
        return output;
    }

}
